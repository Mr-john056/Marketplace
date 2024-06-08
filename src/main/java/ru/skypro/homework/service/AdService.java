package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.exception.AdNotFoundException;

import java.io.IOException;

public interface AdService {

     /**
     * /* Добавляет новое объявление.
     *
     *       @param createOrUpdateAdDto DTO с информацией о новом объявлении.
     *       @param image              Изображение объявления.
     *       @param authentication      Аутентификация пользователя.
     *       @return DTO с информацией о созданном объявлении.
     *       @throws IOException Если произошла ошибка при загрузке изображения.
     *      */
    AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication) throws IOException;

     /**
     * Возвращает список всех объявлений.
     *
     *       @return DTO со списком всех объявлений.
     */
    AdsDto getAll();

     /**
     * Возвращает список объявлений пользователя.
     *
     *       @param username Имя пользователя.
     *       @return DTO со списком объявлений пользователя.
     */
    AdsDto getMyAds(String username);

     /**
     * Возвращает объявление по ID.
     *
     *       @param id ID объявления.
     *       @return Объявление.
     */
    Ad getAd(Integer id);

     /**
     *  Возвращает изображение объявления.
     *
     *       @param id ID объявления.
     *       @return Изображение объявления.
     *       @throws IOException Если произошла ошибка при чтении изображения.
     */
    byte[] getImage(Integer id) throws IOException;

     /**
     * Возвращает подробную информацию о объявлении.
     *
     *       @param id ID объявления.
     *       @return DTO с подробной информацией о объявлении.
     */
    ExtendedAdDto getExtendedAd(Integer id);

     /**
     * Обновляет объявление.
     *
     *       @param id                    Идентификатор объявления.
     *       @param createOrUpdateAdDto    Данные для обновления объявления.
     *       @param authentication        Аутентификация пользователя.
     *       @return Обновленное объявление в виде DTO.
     */
    AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication);

     /**
     * Обновляет изображение объявления.
     *
     *       @param id       Идентификатор объявления.
     *       @param image    Новый файл изображения.
     *       @param authentication Аутентификация пользователя.
     *       @return Бинарные данные обновленного изображения.
     *       @throws IOException          Ошибка ввода-вывода.
     */
    byte[] updateAdImage(Integer id, MultipartFile image, Authentication authentication) throws IOException;

    /**
     * Удаляет объявление.
     *
     *       @param id                    Идентификатор объявления.
     *       @param authentication        Аутентификация пользователя.
     *       @throws AdNotFoundException  Исключение, если объявление не найдено.
     */
    void deleteAd(Integer id, Authentication authentication);
}
