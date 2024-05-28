package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private int createdAt;
    private String text;
    @JoinColumn()
    @ManyToOne
    Ad ad;

    @JoinColumn()
    @ManyToOne
    User user;
}
