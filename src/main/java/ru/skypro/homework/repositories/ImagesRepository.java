package ru.skypro.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Image;

public interface ImagesRepository extends JpaRepository<Image, Integer> {
    Image findByPath(String path);
}
