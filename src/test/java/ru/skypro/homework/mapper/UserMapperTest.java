package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @Test
    public void testToDto() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("123456789");
        user.setRole(Role.valueOf("USER"));

        UserDto userDto = UserMapper.toDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getPhone(), userDto.getPhone());
        assertEquals(user.getRole(), userDto.getRole());
        assertEquals("/users/test@example.com/image", userDto.getImage());
    }

    @Test
    public void testToEntity() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("test@example.com");
        registerDto.setFirstName("Jane");
        registerDto.setLastName("Smith");
        registerDto.setPhone("987654321");
        registerDto.setRole(Role.valueOf("ADMIN"));

        User user = UserMapper.toEntity(registerDto);

        assertEquals(registerDto.getUsername().toLowerCase(), user.getEmail());
        assertEquals(registerDto.getPhone(), user.getPhone());
        assertEquals(registerDto.getFirstName(), user.getFirstName());
        assertEquals(registerDto.getLastName(), user.getLastName());
        assertEquals(registerDto.getRole(), user.getRole());
    }

}