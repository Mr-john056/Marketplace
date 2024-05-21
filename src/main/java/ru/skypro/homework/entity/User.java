package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Value("${path.to.user.images}")
    private String image;

}
