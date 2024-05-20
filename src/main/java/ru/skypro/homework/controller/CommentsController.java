package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getAllComments(@PathVariable Integer id) {
        return (ResponseEntity<CommentsDto>) ResponseEntity.ok();
    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return (ResponseEntity<CommentDto>) ResponseEntity.ok();
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment (@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return (ResponseEntity<CommentDto>) ResponseEntity.ok();
    }
}
