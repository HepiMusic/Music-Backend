package com.hepi.music_api.artist;

import com.hepi.music_api.security.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    private String stageName;
    private String bio;
    private String profilePicture; // URL or base64-encoded image
    private String thumbnailImage;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}
