package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentsDtoTest {
    @Test
    public void testGetCount() {
        int count = 5;
        List<CommentDto> results = new ArrayList<CommentDto>();

        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(count);
        commentsDto.setResults(results);

        assertEquals(count, commentsDto.getCount());
    }

    @Test
    public void testGetResults() {
        int count = 5;
        List<CommentDto> results = new ArrayList<CommentDto>();
        results.add(new CommentDto());
        results.add(new CommentDto());

        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(count);
        commentsDto.setResults(results);

        assertEquals(results, commentsDto.getResults());
    }


}