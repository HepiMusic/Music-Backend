package com.hepi.music_api.comment.repository;

import com.hepi.music_api.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySongId(Long songId);
    List<Comment> findByUserId(Long userId);
}
