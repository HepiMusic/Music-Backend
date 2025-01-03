package com.hepi.music_api.comment.service.impl;

import com.hepi.music_api.comment.dto.CommentDTO;
import com.hepi.music_api.comment.model.Comment;
import com.hepi.music_api.comment.repository.CommentRepository;
import com.hepi.music_api.comment.service.CommentService;
import com.hepi.music_api.exception.ResourceNotFoundException;
import com.hepi.music_api.security.user.model.User;
import com.hepi.music_api.security.user.repository.UserRepository;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.songs.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, 
                              SongRepository songRepository, 
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment addComment(CommentDTO commentDTO) {
        Song song = songRepository.findBySongId(commentDTO.getSongId())
            .orElseThrow(() -> new ResourceNotFoundException("Song not found with ID: " + commentDTO.getSongId()));

        User user = userRepository.findById(commentDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + commentDTO.getUserId()));

        Comment comment = new Comment();
        comment.setSong(song);
        comment.setUser(user);
        comment.setContent(commentDTO.getContent());


        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsBySong(Long songId) {
        return commentRepository.findAllBySong_SongId(songId);
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findAllByUser_Id(userId);
    }

    @Override
    public Comment updateComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + commentId));

        if (commentDTO.getContent() != null) {
            comment.setContent(commentDTO.getContent());
        }
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found with ID: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }
}
