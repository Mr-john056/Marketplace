package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewPasswordDtoTest {
    @Test
    void testGetSetCurrentPassword() {
        String currentPassword = "oldPassword";
        NewPasswordDto newPasswordDto = new NewPasswordDto();

        newPasswordDto.setCurrentPassword(currentPassword);

        assertEquals(currentPassword, newPasswordDto.getCurrentPassword());
    }

    @Test
    void testGetSetNewPassword() {
        String newPassword = "newPassword";
        NewPasswordDto newPasswordDto = new NewPasswordDto();

        newPasswordDto.setNewPassword(newPassword);

        assertEquals(newPassword, newPasswordDto.getNewPassword());
    }

}