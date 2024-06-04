package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDtoTest {
    @Test
    public void testConstructor() {
        RegisterDto registerDto = new RegisterDto();
        assertNotNull(registerDto);
    }

    @Test
    public void testGettersAndSetters() {
        RegisterDto registerDto = new RegisterDto();

        registerDto.setUsername("john_doe");
        assertEquals("john_doe", registerDto.getUsername());

        registerDto.setPassword("pass123");
        assertEquals("pass123", registerDto.getPassword());

        registerDto.setFirstName("John");
        assertEquals("John", registerDto.getFirstName());

        registerDto.setLastName("Doe");
        assertEquals("Doe", registerDto.getLastName());

        registerDto.setPhone("1234567890");
        assertEquals("1234567890", registerDto.getPhone());

        registerDto.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, registerDto.getRole());
    }

}