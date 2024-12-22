package com.hepi.music_api.subscription;

import com.hepi.music_api.security.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String planName; // FREE, PREMIUM
    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isActive;

    // Getters and Setters
}
