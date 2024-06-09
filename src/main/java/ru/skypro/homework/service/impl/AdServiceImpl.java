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
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.CommentRepository;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    @Value("${path.to.ad.photo}")
    private String photoPath;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UsernameNotFoundException(authentication.getName()));
        Ad ad = adMapper.toEntity(createOrUpdateAdDto);
        ad.setUser(user);
        ad = adRepository.save(ad);
        return adMapper.toAdDto(adRepository.save(uploadImage(ad, image)));
    }
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
    @Override
    public AdsDto getAll() {
        return adMapper.toAdsDto(adRepository.findAll());
    }
    @Override
    public AdsDto getMyAds(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return adMapper.toAdsDto(adRepository.findAllByUserId(user.getId()));
    }
    @Override
    public Ad getAd(Integer id) {
        return adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
    }
    @Override
    public byte[] getImage(Integer id) throws IOException {
        Ad ad = getAd(id);
        return Files.readAllBytes(Path.of(ad.getImage()));
    }
    @Override
    public ExtendedAdDto getExtendedAd(Integer id) {
        return adMapper.toExtendedAdDto(getAd(id));
    }
    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication) {
        Ad ad = getAd(id);
        ad.setDescription(createOrUpdateAdDto.getDescription());
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        return adMapper.toAdDto(adRepository.save(ad));
    }
    @Override
    public byte[] updateAdImage(Integer id, MultipartFile image, Authentication authentication) throws IOException {

        Ad ad = getAd(id);
        ad = uploadImage(ad, image);
        return Files.readAllBytes(Path.of(ad.getImage()));


    }
    @Override
    public void deleteAd(Integer id, Authentication authentication) throws AdNotFoundException {
        if (adRepository.existsById(id)) {

            List<Comment> comments = commentRepository.findByAdPk(id);
            for (Comment c : comments)
                commentRepository.delete(c);
            
            adRepository.delete(getAd(id));
        } else {
            throw new AdNotFoundException(id);
        }
    }

}
