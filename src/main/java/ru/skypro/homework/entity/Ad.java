package ru.skypro.homework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@EqualsAndHashCode(of = "pk")
@Data
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @Value("${path.to.ad.photo}")
    private String image;
    private Integer price;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn
    private User user;
}
