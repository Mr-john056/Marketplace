package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommentMapper {
    public Comment toEntity (CreateOrUpdateCommentDto createOrUpdateCommentDto){
        Comment comment = new Comment();
        comment.setText(createOrUpdateCommentDto.getText());
        return comment;
    }

    public CommentDto toCommentDto(Comment comment){

        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getPk());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setText(comment.getText());
        commentDto.setAuthor(comment.getUser().getId());
        commentDto.setAuthorFirstName(comment.getUser().getFirstName());
        commentDto.setAuthorImage(comment.getUser().getImage());

        return commentDto;
    }

    public CommentsDto toCommentsDto(List<Comment> comments) {
        CommentsDto commentsDto = new CommentsDto();
        List<CommentDto> commentDtoList = comments.stream()
                .map(this::toCommentDto)
                .collect(Collectors.toList());

        commentsDto.setCount(commentDtoList.size());
        commentsDto.setResults(commentDtoList);

        return commentsDto;
    }
}
