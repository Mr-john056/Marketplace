package ru.skypro.homework.service;

import ru.skypro.homework.dto.LoginDto;

public interface AuthService {

     /**
     * Проверяет валидность логина пользователя.
     *       @param userName Имя пользователя.
     *       @param password Пароль пользователя.
     *       @return true, если логин и пароль верны, false - иначе.
     */
    boolean login(String userName, String password);

     /**
     * Проверяет валидность логина пользователя.
     *       @param loginDto Объект, содержащий имя пользователя и пароль.
     *       @return true, если логин и пароль верны, false - иначе.
     */
    boolean login(LoginDto loginDto);

}
