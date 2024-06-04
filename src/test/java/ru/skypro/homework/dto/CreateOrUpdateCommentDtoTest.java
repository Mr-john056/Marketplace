package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrUpdateCommentDtoTest {
    @Test
    public void testGetSetText() {
        CreateOrUpdateCommentDto commentDto = new CreateOrUpdateCommentDto();
        String text = "This is a test comment";

        commentDto.setText(text);
        assertEquals(text, commentDto.getText());
    }

}