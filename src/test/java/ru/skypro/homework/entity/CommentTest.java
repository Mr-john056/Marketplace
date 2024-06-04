package ru.skypro.homework.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    @Test
    public void testCommentConstructor() {
        Comment comment = new Comment(1, 123456789L, "Test comment", new Ad(), new User());
        assertEquals(1, comment.getPk());
        assertEquals(123456789L, comment.getCreatedAt());
        assertEquals("Test comment", comment.getText());
        assertNotNull(comment.getAd());
        assertNotNull(comment.getUser());
    }

    @Test
    public void testCommentGettersAndSetters() {
        Comment comment = new Comment();
        comment.setPk(1);
        comment.setCreatedAt(123456789L);
        comment.setText("Test comment");
        comment.setAd(new Ad());
        comment.setUser(new User());

        assertEquals(1, comment.getPk());
        assertEquals(123456789L, comment.getCreatedAt());
        assertEquals("Test comment", comment.getText());
        assertNotNull(comment.getAd());
        assertNotNull(comment.getUser());
    }

}