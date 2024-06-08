package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {
    private final UserRepository userRepository;
    private final CommentServiceImpl commentService;
    private final AdServiceImpl adService;

    public CommentsController(UserRepository userRepository, CommentServiceImpl commentService, AdServiceImpl adService) {
        this.userRepository = userRepository;
        this.commentService = commentService;
        this.adService = adService;
    }

    @GetMapping("/{id}/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentsDto> getAllComments(@PathVariable Integer id, Authentication authentication){
        try {
            return ResponseEntity.ok(commentService.getComments(id, authentication));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/{id}/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> addComment (@PathVariable("id") Integer id,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto,
            Authentication authentication){
        try {
            commentService.addComment(id, createOrUpdateCommentDto, authentication);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentService.addComment(id, createOrUpdateCommentDto, authentication));
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') or @commentServiceImpl.getComment(#commentId).user.email == authentication.principal.username")
    public ResponseEntity<?> deleteComment ( @PathVariable int adId, @PathVariable int commentId, Authentication
    authentication){
        try {
            commentService.deleteComment(adId, commentId, authentication);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("{adId}/comments/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN') or @commentServiceImpl.getComment(#commentId).user.email == authentication.principal.username")
    public ResponseEntity<CommentDto> updateComment (@PathVariable Integer adId, @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto,
            Authentication authentication){
        try {
            commentService.updateComment(adId, commentId, createOrUpdateCommentDto, authentication);
        } catch (NotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, createOrUpdateCommentDto, authentication));
    }
}

