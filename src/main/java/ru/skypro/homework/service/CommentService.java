package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.exception.NotFoundException;

public interface CommentService {

     /**
     * Возвращает список комментариев к объявлению.
     *
     *       @param adPk            Идентификатор объявления
     *       @param authentication Аутентификация пользователя
     *       @return Список комментариев к объявлению
     *       @throws NotFoundException Если объявление не найдено
     */
    CommentsDto getComments(Integer adPk, Authentication authentication);

     /**
     * Добавляет новый комментарий к объявлению.
     *
     *       @param dto           Данные нового комментария
     *       @param authentication Аутентификация пользователя
     *       @return Созданный комментарий
     *       @throws NotFoundException Если объявление не найдено
     */
    CommentDto addComment(Integer adPk, CreateOrUpdateCommentDto dto, Authentication authentication);

     /**
     *  Обновляет комментарий.
     *
     *       @param adPk                       Идентификатор объявления
     *       @param commentId                   Идентификатор комментария
     *       @param createOrUpdateCommentDto Данные для обновления комментария
     *       @param authentication             Аутентификация пользователя
     *       @return Обновленный комментарий
     *       @throws NotFoundException Если комментарий не найден
     */
    CommentDto updateComment(Integer adPk, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication);

     /**
     * Возвращает комментарий по идентификатору.
     *
     *       @param pk Идентификатор комментария
     *       @return Комментарий
     *       @throws NotFoundException Если комментарий не найден
     */
    Comment getComment(Integer pk);

     /**
     * Удаляет комментарий.
     *
     *       @param adId           Идентификатор объявления
     *       @param commentId      Идентификатор комментария
     *       @param authentication Аутентификация пользователя
     *       @throws NotFoundException Если комментарий не найден
     */
    void deleteComment(Integer adId, Integer commentId, Authentication authentication);
}
