package com.hepi.music_api.comment.service;

import com.hepi.music_api.comment.dto.CommentDTO;
import com.hepi.music_api.comment.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(CommentDTO commentDTO);
    List<Comment> getCommentsBySong(Long songId);
    List<Comment> getCommentsByUser(Long userId);
    Comment updateComment(Long commentId, CommentDTO commentDTO);
    void deleteComment(Long commentId);
}
