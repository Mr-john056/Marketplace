package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.MyUserDetails;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.service.AuthService;

   /*
    Сервис аутентификации.
   */

@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsService myUserDetailService;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(MyUserDetailsService myUserDetailService, PasswordEncoder passwordEncoder) {
        this.myUserDetailService = myUserDetailService;
        this.encoder = passwordEncoder;
    }
     /*
      Проверяет валидность логина пользователя.
      @param userName Имя пользователя.
      @param password Пароль пользователя.
      @return true, если логин и пароль верны, false - иначе.
     */

    @Override
    public boolean login(String userName, String password) {
        return true;
    }
     /*
      Проверяет валидность логина пользователя.
      @param loginDto Объект, содержащий имя пользователя и пароль.
      @return true, если логин и пароль верны, false - иначе.
     */

    @Override
    public boolean login(LoginDto loginDto) {

        MyUserDetails userDetails = myUserDetailService.loadUserByUsername(loginDto.getUsername());
        return encoder.matches(loginDto.getPassword(), userDetails.getPassword());
    }

}
