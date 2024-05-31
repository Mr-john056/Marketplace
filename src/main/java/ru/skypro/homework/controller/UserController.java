package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.PasswordIsNotCorrectException;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto dto, Authentication authentication) {
        try {
            userService.updatePassword(dto, authentication.getName());
        } catch (PasswordIsNotCorrectException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo(Authentication authentication) {
        return ResponseEntity.ok(userService.getInfoAboutMe(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@Valid @RequestBody UpdateUserDto dto, Authentication authentication) {
        return ResponseEntity.ok(userService.updateInfoAboutMe(authentication.getName(), dto));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestParam MultipartFile image, Authentication authentication) throws IOException {
        userService.updateMyImage(authentication.getName(), image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "{username}/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getUserImage(@PathVariable String username) {
        return ResponseEntity.ok(userService.getImage(username));
    }
}
