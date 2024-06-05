package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

}
