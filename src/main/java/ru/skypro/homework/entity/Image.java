package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;
    private Long size;
    private String contentType;
}
