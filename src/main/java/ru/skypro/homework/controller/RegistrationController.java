package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.RegisterDto;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class RegistrationController {
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto register) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
}
