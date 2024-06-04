package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserDtoTest {
    @Test
    public void testSetFirstName() {
        UpdateUserDto userDto = new UpdateUserDto();
        userDto.setFirstName("John");
        assertEquals("John", userDto.getFirstName());
    }

    @Test
    public void testSetLastName() {
        UpdateUserDto userDto = new UpdateUserDto();
        userDto.setLastName("Doe");
        assertEquals("Doe", userDto.getLastName());
    }

    @Test
    public void testSetPhone() {
        UpdateUserDto userDto = new UpdateUserDto();
        userDto.setPhone("123-456-7890");
        assertEquals("123-456-7890", userDto.getPhone());
    }

}