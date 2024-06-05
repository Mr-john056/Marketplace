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
    private long createdAt;
    private String text;
    @JoinColumn()
    @ManyToOne
    private Ad ad;

    @JoinColumn()
    @ManyToOne
    private User user;
}
