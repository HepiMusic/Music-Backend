package com.hepi.music_api.vote.repository;

import com.hepi.music_api.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findBySongIdAndUserId(Long songId, Long userId);
}