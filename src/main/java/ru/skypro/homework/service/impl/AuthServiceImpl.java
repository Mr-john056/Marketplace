package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.MyUserDetails;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsService myUserDetailService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(MyUserDetailsService myUserDetailService,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.myUserDetailService = myUserDetailService;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean login(LoginDto loginDto) {

        MyUserDetails userDetails = myUserDetailService.loadUserByUsername(loginDto.getUsername());
        return encoder.matches(loginDto.getPassword(), userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if(userRepository.findByEmail(registerDto.getUsername()).isPresent()) {
            return false;
        } else {
            //userService.registerUser(registerDto);
            return true;
        }
    }

}
