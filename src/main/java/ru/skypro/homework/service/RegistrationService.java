package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;

public interface RegistrationService {
    boolean register(RegisterDto registerDto);
}
