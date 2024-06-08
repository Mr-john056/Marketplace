package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;

public interface RegistrationService {

     /**
     * Регистрирует нового пользователя.
     *
     *       @param registerDto Данные для регистрации нового пользователя.
     *       @return true, если регистрация прошла успешно, иначе false.
     */
    boolean register(RegisterDto registerDto);
}
