package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.PasswordIsNotCorrectException;
import ru.skypro.homework.exception.UserAlreadyRegisteredException;

import java.io.IOException;

public interface UserService {

     /**
     * Обновляет пароль пользователя.
     *
     *       @param dto             Объект, содержащий новый пароль и текущий пароль.
     *       @param username       Имя пользователя.
     *       @throws PasswordIsNotCorrectException Если текущий пароль неверен.
     *       @throws UsernameNotFoundException    Если пользователь с данным именем не найден.
     */
    void updatePassword(NewPasswordDto dto, String username);

     /**
     *  Возвращает изображение пользователя по его имени.
     *
     *       @param username Имя пользователя.
     *       @return Массив байтов, представляющий изображение.
     *       @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */
    byte[] getImage(String username);

     /**
     * Возвращает информацию о текущем пользователе.
     *
     *       @param username Имя пользователя.
     *       @return Объект UserDto, содержащий информацию о пользователе.
     *       @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */
    UserDto getInfoAboutMe(String username);

     /**
     * Обновляет информацию о текущем пользователе.
     *
     *       @param username Имя пользователя.
     *       @param dto      Объект UpdateUserDto, содержащий обновленные данные.
     *       @return Объект UpdateUserDto, содержащий обновленные данные.
     *       @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */
    UpdateUserDto updateInfoAboutMe(String username, UpdateUserDto dto);

     /**
     * Обновляет изображение текущего пользователя.
     *
     *       @param username Имя пользователя.
     *       @param file     Файл изображения.
     *       @throws IOException             Если произошла ошибка при чтении файла или записи изображения.
     *       @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */
    void updateMyImage(String username, MultipartFile file) throws IOException;

     /**
     *  Регистрирует нового пользователя.
     *
     *       @param dto Данные для регистрации пользователя.
     *       @return Зарегистрированный пользователь.
     *       @throws UserAlreadyRegisteredException Если пользователь с таким email уже существует.
     */
    User registerUser(RegisterDto dto);
}
