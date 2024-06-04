package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDtoTest {
    @Test
    public void testGetPassword() {
        LoginDto loginDto = new LoginDto();
        loginDto.setPassword("password123");
        assertEquals("password123", loginDto.getPassword());
    }

    @Test
    public void testGetUsername() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("username123");
        assertEquals("username123", loginDto.getUsername());
    }

    @Test
    public void testSetPassword() {
        LoginDto loginDto = new LoginDto();
        loginDto.setPassword("newPassword");
        assertEquals("newPassword", loginDto.getPassword());
    }

    @Test
    public void testSetUsername() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("newUsername");
        assertEquals("newUsername", loginDto.getUsername());
    }

}