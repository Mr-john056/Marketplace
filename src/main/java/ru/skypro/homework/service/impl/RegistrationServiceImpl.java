package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    public RegistrationServiceImpl(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if(userRepository.findByEmail(registerDto.getUsername()).isPresent()) {
            return false;
        } else {
            userService.registerUser(registerDto);
            return true;
        }
    }
}
