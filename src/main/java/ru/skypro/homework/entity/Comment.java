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
    @JoinColumn()
    @ManyToOne
    Ad ad;

    @JoinColumn()
    @ManyToOne
    User user;

    int createdAt;

    String text;
}
