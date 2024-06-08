package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.CommentRepository;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.CommentService;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adsRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    /*
      Возвращает список комментариев к объявлению.

      @param adPk            Идентификатор объявления
      @param authentication Аутентификация пользователя
      @return Список комментариев к объявлению
      @throws NotFoundException Если объявление не найдено
     */
    @Override
    public CommentsDto getComments(Integer adPk, Authentication authentication) {
        if (adsRepository.existsById(adPk)) {
            return commentMapper.toCommentsDto(commentRepository.findByAdPk(adPk));
        }
        else {
            throw new NotFoundException("Ad is not found");}
    }
     /*
      Добавляет новый комментарий к объявлению.

      @param pk            Идентификатор объявления
      @param dto           Данные нового комментария
      @param authentication Аутентификация пользователя
      @return Созданный комментарий
      @throws NotFoundException Если объявление не найдено
     */


    public CommentDto addComment(Integer pk, CreateOrUpdateCommentDto dto, Authentication authentication) {
        if (adsRepository.existsById(pk)){
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(RuntimeException::new);
            Ad ad = adsRepository.findById(pk).orElse(null);
            Comment comment = commentMapper.toEntity(dto);
            comment.setUser(user);
            comment.setAd(ad);
            comment.setText(dto.getText());
            comment.setCreatedAt(System.currentTimeMillis());
            return commentMapper.toCommentDto(commentRepository.save(comment));}
        else {
            throw new NotFoundException("Ad is not found");}
    }
     /*
      Обновляет комментарий.

      @param adPk                       Идентификатор объявления
      @param commentId                   Идентификатор комментария
      @param createOrUpdateCommentDto Данные для обновления комментария
      @param authentication             Аутентификация пользователя
      @return Обновленный комментарий
      @throws NotFoundException Если комментарий не найден
     */
    @Override
    public CommentDto updateComment(Integer adPk,
                                    Integer commentId,
                                    CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                    Authentication authentication) throws NotFoundException{
        if (commentRepository.existsById(commentId)){
            Comment comment = getComment(commentId);
            comment.setText(createOrUpdateCommentDto.getText());
            return commentMapper.toCommentDto(commentRepository.save(comment));}
        else {
            throw new NotFoundException("Comment is not found");}
    }
     /*
      Возвращает комментарий по идентификатору.

      @param pk Идентификатор комментария
      @return Комментарий
      @throws NotFoundException Если комментарий не найден
     */
    @Override
    public Comment getComment(Integer pk) {
        return commentRepository.findById(pk).orElseThrow();
    }
     /*
      Удаляет комментарий.

      @param adId           Идентификатор объявления
      @param commentId      Идентификатор комментария
      @param authentication Аутентификация пользователя
      @throws NotFoundException Если комментарий не найден
     */
    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) throws NotFoundException {
        if (commentRepository.existsById(commentId)) {
            commentRepository.delete(getComment(commentId));
        } else {
            throw new NotFoundException("Comment is not found");
        }

    }
}
