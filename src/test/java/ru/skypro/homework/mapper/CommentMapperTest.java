package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.*;

class CommentMapperTest {
    @Test
    public void testToEntity() {
        CommentMapper commentMapper = new CommentMapper();
        CreateOrUpdateCommentDto createOrUpdateCommentDto = new CreateOrUpdateCommentDto();
        createOrUpdateCommentDto.setText("Test Comment");

        Comment comment = commentMapper.toEntity(createOrUpdateCommentDto);

        assertEquals("Test Comment", comment.getText());
    }

    @Test
    public void testToCommentDto() {
        CommentMapper commentMapper = new CommentMapper();
        Comment comment = new Comment();
        comment.setPk(1);
        comment.setText("Test Comment");
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setEmail("mail");
        user.setImage("profile.jpg");
        comment.setUser(user);

        CommentDto commentDto = commentMapper.toCommentDto(comment);

        assertEquals(1L, commentDto.getPk());
        assertEquals("Test Comment", commentDto.getText());
        assertEquals(1L, commentDto.getAuthor());
        assertEquals("John", commentDto.getAuthorFirstName());
        assertEquals("/users/mail/image", commentDto.getAuthorImage());
    }

    @Test
    public void testToCommentsDto() {
        CommentMapper commentMapper = new CommentMapper();
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setPk(1);
        comment1.setText("Test Comment 1");
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setImage("profile.jpg");
        comment1.setUser(user1);
        Comment comment2 = new Comment();
        comment2.setPk(2);
        comment2.setText("Test Comment 2");
        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Jane");
        user2.setImage("profile2.jpg");
        comment2.setUser(user2);
        comments.add(comment1);
        comments.add(comment2);

        CommentsDto commentsDto = commentMapper.toCommentsDto(comments);

        assertEquals(2, commentsDto.getCount());
        assertEquals(2, commentsDto.getResults().size());
    }
}