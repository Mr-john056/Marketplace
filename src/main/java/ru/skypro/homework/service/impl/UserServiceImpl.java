package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.PasswordIsNotCorrectException;
import ru.skypro.homework.exception.UserAlreadyRegisteredException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    @Value("${path.to.user.images}")
    private String imagePath;
    @Value("${path.to.default.user.image}")
    private String pathToDefaultUserImage;
    @Autowired

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }
     /*
      Обновляет пароль пользователя.

      @param dto             Объект, содержащий новый пароль и текущий пароль.
      @param username       Имя пользователя.
      @throws PasswordIsNotCorrectException Если текущий пароль неверен.
      @throws UsernameNotFoundException    Если пользователь с данным именем не найден.
     */

    @Override
    public void updatePassword(NewPasswordDto dto, String username) throws PasswordIsNotCorrectException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        if (encoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(dto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new PasswordIsNotCorrectException();
        }


    }
     /*
      Возвращает изображение пользователя по его имени.

      @param username Имя пользователя.
      @return Массив байтов, представляющий изображение.
      @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */

    @Override
    public byte[] getImage(String username) {
        try {
            User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username is not found"));
            if (user.getImage() == null) {
                user.setImage(pathToDefaultUserImage);
            }
            return Files.readAllBytes(Path.of(user.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     /*
      Возвращает информацию о текущем пользователе.

      @param username Имя пользователя.
      @return Объект UserDto, содержащий информацию о пользователе.
      @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */

    @Override
    public UserDto getInfoAboutMe(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        return UserMapper.toDto(user);
    }
     /*
      Обновляет информацию о текущем пользователе.

      @param username Имя пользователя.
      @param dto      Объект UpdateUserDto, содержащий обновленные данные.
      @return Объект UpdateUserDto, содержащий обновленные данные.
      @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */

    @Override
    public UpdateUserDto updateInfoAboutMe(String username, UpdateUserDto dto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        userRepository.save(user);
        return dto;
    }
     /*
      Обновляет изображение текущего пользователя.

      @param username Имя пользователя.
      @param file     Файл изображения.
      @throws IOException             Если произошла ошибка при чтении файла или записи изображения.
      @throws UsernameNotFoundException Если пользователь с данным именем не найден.
     */

    @Override
    public void updateMyImage(String username, MultipartFile file) throws IOException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        uploadImage(user, file);
    }
     /*
      Регистрирует нового пользователя.

      @param dto Данные для регистрации пользователя.
      @return Зарегистрированный пользователь.
      @throws UserAlreadyRegisteredException Если пользователь с таким email уже существует.
     */


    @Override
    public User registerUser(RegisterDto dto) {
        if (userRepository.findByEmail(dto.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException(dto.getUsername());
        } else {
            User user = userMapper.toEntity(dto);
            user.setPassword(encoder.encode(dto.getPassword()));
            return userRepository.save(user);
        }
    }
     /*
      Загружает изображение пользователя.

      @param user Пользователь, для которого загружается изображение.
      @param file Файл с изображением.
      @throws IOException Если произошла ошибка во время записи файла.
     */

    public void uploadImage(User user, MultipartFile file) throws IOException {
        Path path = Path.of(imagePath, user.getEmail() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename()));


        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);

        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(path, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
            user.setImage(path.toString());
            userRepository.save(user);
        }
    }
}
