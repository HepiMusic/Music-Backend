package com.hepi.music_api.comment.repository;

import com.hepi.music_api.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllBySong_SongId(Long songId);
    List<Comment> findAllByUser_Id(Long userId);

}
