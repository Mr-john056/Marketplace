package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.io.IOException;

public interface UserService {
    public void updatePassword(NewPasswordDto dto, String username);

    byte[] getImage(String username);

    public UserDto getInfoAboutMe(String username);
    public UpdateUserDto updateInfoAboutMe(String username, UpdateUserDto dto);
    public void updateMyImage(String username, MultipartFile file) throws IOException;
    public User registerUser(RegisterDto dto);
}
