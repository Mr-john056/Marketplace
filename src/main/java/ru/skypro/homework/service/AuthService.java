package ru.skypro.homework.service;

import ru.skypro.homework.dto.LoginDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean login(LoginDto loginDto);

}
