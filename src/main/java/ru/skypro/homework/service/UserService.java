package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.io.IOException;

public interface UserService {
    void updatePassword(NewPasswordDto dto, String username);

    byte[] getImage(String username);

    UserDto getInfoAboutMe(String username);
    UpdateUserDto updateInfoAboutMe(String username, UpdateUserDto dto);
    void updateMyImage(String username, MultipartFile file) throws IOException;
    User registerUser(RegisterDto dto);
}
