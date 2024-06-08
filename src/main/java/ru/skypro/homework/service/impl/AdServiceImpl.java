package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    @Value("${path.to.ad.photo}")
    private String photoPath;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

     /* Добавляет новое объявление.

      @param createOrUpdateAdDto DTO с информацией о новом объявлении.
      @param image              Изображение объявления.
      @param authentication      Аутентификация пользователя.
      @return DTO с информацией о созданном объявлении.
      @throws IOException Если произошла ошибка при загрузке изображения.
     */
    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UsernameNotFoundException(authentication.getName()));
        Ad ad = adMapper.toEntity(createOrUpdateAdDto);
        ad.setUser(user);
        ad = adRepository.save(ad);
        return adMapper.toAdDto(adRepository.save(uploadImage(ad, image)));
    }
     /* Загружает изображение объявления.

      @param ad   Объявление, к которому относится изображение.
      @param image Изображение объявления.
      @return Объявление с обновленным путем к изображению.
      @throws IOException Если произошла ошибка при загрузке изображения.

     */

    private Ad uploadImage(Ad ad, MultipartFile image) throws IOException {
        Path filePath = Path.of(photoPath, ad.hashCode() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
            ad.setImage(filePath.toString());
            return adRepository.save(ad);
        }

    }
     /* Возвращает список всех объявлений.

      @return DTO со списком всех объявлений.
     */

    @Override
    public AdsDto getAll() {
        return adMapper.toAdsDto(adRepository.findAll());
    }
     /* Возвращает список объявлений пользователя.

      @param username Имя пользователя.
      @return DTO со списком объявлений пользователя.
     */

    @Override
    public AdsDto getMyAds(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return adMapper.toAdsDto(adRepository.findAllByUserId(user.getId()));
    }
     /* Возвращает объявление по ID.

      @param id ID объявления.
      @return Объявление.
     */

    @Override
    public Ad getAd(Integer id) {
        return adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
    }
     /*  Возвращает изображение объявления.

      @param id ID объявления.
      @return Изображение объявления.
      @throws IOException Если произошла ошибка при чтении изображения.
     */

    @Override
    public byte[] getImage(Integer id) throws IOException {
        Ad ad = getAd(id);
        return Files.readAllBytes(Path.of(ad.getImage()));
    }
     /*  Возвращает подробную информацию о объявлении.

      @param id ID объявления.
      @return DTO с подробной информацией о объявлении.
     */

    @Override
    public ExtendedAdDto getExtendedAd(Integer id) {
        return adMapper.toExtendedAdDto(getAd(id));
    }
     /*  Обновляет объявление.

      @param id                    Идентификатор объявления.
      @param createOrUpdateAdDto    Данные для обновления объявления.
      @param authentication        Аутентификация пользователя.
      @return                     Обновленное объявление в виде DTO.
     */


    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication) {
        Ad ad = getAd(id);
        ad.setDescription(createOrUpdateAdDto.getDescription());
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        return adMapper.toAdDto(adRepository.save(ad));
    }
     /*  Обновляет изображение объявления.

      @param id       Идентификатор объявления.
      @param image    Новый файл изображения.
      @param authentication Аутентификация пользователя.
      @return         Бинарные данные обновленного изображения.
      @throws IOException          Ошибка ввода-вывода.
     */

    @Override
    public byte[] updateAdImage(Integer id, MultipartFile image, Authentication authentication) throws IOException {

        Ad ad = getAd(id);
        ad = uploadImage(ad, image);
        return Files.readAllBytes(Path.of(ad.getImage()));


    }
     /* Удаляет объявление.

      @param id                    Идентификатор объявления.
      @param authentication        Аутентификация пользователя.
      @throws AdNotFoundException  Исключение, если объявление не найдено.
     */

    @Override
    public void deleteAd(Integer id, Authentication authentication) throws AdNotFoundException {
        if (adRepository.existsById(id)) {
            adRepository.delete(getAd(id));
        } else {
            throw new AdNotFoundException(id);
        }
    }

}
