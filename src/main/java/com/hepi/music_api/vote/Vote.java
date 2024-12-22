package com.hepi.music_api.vote;

import com.hepi.music_api.security.user.model.User;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.vote.enums.VoteType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Enumerated(EnumType.STRING)
    private VoteType voteType; // UPVOTE or DOWNVOTE

    private LocalDateTime createdAt = LocalDateTime.now();


}
