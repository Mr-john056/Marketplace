package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    @Test
    public void testUserDtoConstructor() {
        UserDto userDto = new UserDto();
        assertNotNull(userDto);
    }

    @Test
    public void testUserDtoGettersAndSetters() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("test@example.com");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPhone("1234567890");
        userDto.setRole(Role.ADMIN);
        userDto.setImage("profile.jpg");

        assertEquals(1, userDto.getId());
        assertEquals("test@example.com", userDto.getEmail());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("1234567890", userDto.getPhone());
        assertEquals(Role.ADMIN, userDto.getRole());
        assertEquals("profile.jpg", userDto.getImage());
    }
}