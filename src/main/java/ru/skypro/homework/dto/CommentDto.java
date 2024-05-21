package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private int createdAd;
    private int pk;
    private String text;
}
