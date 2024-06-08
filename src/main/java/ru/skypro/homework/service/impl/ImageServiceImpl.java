package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repositories.ImagesRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImagesRepository imagesRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarPath;
     /*
      Сохраняет изображение в хранилище.

      @param imageFile Файл изображения.
      @return Сохраненное изображение.
      @throws IOException Если возникла ошибка во время записи файла.
     */

    @Override
    public Image saveImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        createNewPathAndSaveFile(imageFile, image);

        return getSave(image);
    }
     /*
      Возвращает изображение по его идентификатору.

      @param imageId Идентификатор изображения.
      @return Изображение.
     */

    @Override
    public Image getImage(Integer imageId) {
        return imagesRepository.findById(imageId).get();
    }
     /*
      Обновляет изображение по его идентификатору.

      @param imageFile Файл изображения.
      @param imageId  Идентификатор изображения.
      @return Обновленное изображение.
      @throws IOException Если возникла ошибка во время записи файла.
     */

    @Override
    public Image updateImage(MultipartFile imageFile, Integer imageId) throws IOException {
        Image image = getImage(imageId);

        Path path = Path.of(image.getPath());
        Files.deleteIfExists(path);

        Image newPathAndSaveFile = createNewPathAndSaveFile(imageFile, image);
        return getSave(newPathAndSaveFile);
    }
     /*
      Возвращает байты изображения по его пути.

      @param path Путь к изображению.
      @return Байты изображения.
      @throws IOException Если возникла ошибка во время чтения файла.
     */

    @Override
    public byte[] getByteFromFile(String path) throws IOException {
        return Files.readAllBytes(Path.of(avatarPath, path));
    }
     /*
      Сохраняет изображение в базе данных.

      @param image Изображение.
      @return Сохраненное изображение.
     */

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Image getSave(Image image) {
        return imagesRepository.save(image);
    }
     /*
      Создает новый путь для изображения и сохраняет файл.

      @param imageFile Файл изображения.
      @param image     Изображение.
      @return Изображение с новым путем.
      @throws IOException Если возникла ошибка во время записи файла.
     */

    private Image createNewPathAndSaveFile(MultipartFile imageFile, Image image) throws IOException {
        String originalFilename = imageFile.getOriginalFilename();

        String fileName = UUID.randomUUID() + "." + getExtension(Objects.requireNonNull(originalFilename));
        Path path = Path.of(avatarPath, fileName);

        Files.createDirectories(path.getParent());

        readAndWriteInTheDirectory(imageFile, path);

        image.setPath(path.toString());
        image.setContentType(imageFile.getContentType());
        image.setSize(imageFile.getSize());

        return image;
    }
     /*
      Читает файл изображения и записывает его в указанный каталог.

      @param fileImage Файл изображения.
      @param path      Путь к каталогу.
      @throws IOException Если возникла ошибка во время записи файла.
     */

    private void readAndWriteInTheDirectory(MultipartFile fileImage, Path path) throws IOException {
        try (
                InputStream inputStream = fileImage.getInputStream();
                OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE_NEW);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 4096);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 4096);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
    }
     /*
      Возвращает расширение файла.

      @param fileName Имя файла.
      @return Расширение файла.
     */

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
