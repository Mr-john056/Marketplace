package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentDtoTest {
    @Test
    public void testGetAuthor() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(1);
        assertEquals(1, commentDto.getAuthor());
    }

    @Test
    public void testGetAuthorImage() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorImage("image.jpg");
        assertEquals("image.jpg", commentDto.getAuthorImage());
    }

    @Test
    public void testGetAuthorFirstName() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorFirstName("John");
        assertEquals("John", commentDto.getAuthorFirstName());
    }

    @Test
    public void testGetCreatedAt() {
        CommentDto commentDto = new CommentDto();
        commentDto.setCreatedAt(1234567890);
        assertEquals(1234567890, commentDto.getCreatedAt());
    }

    @Test
    public void testGetPk() {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(1001);
        assertEquals(1001, commentDto.getPk());
    }

    @Test
    public void testGetText() {
        CommentDto commentDto = new CommentDto();
        commentDto.setText("This is a comment");
        assertEquals("This is a comment", commentDto.getText());
    }

}