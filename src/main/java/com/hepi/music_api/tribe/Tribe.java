package com.hepi.music_api.tribe;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "tribes")
public class Tribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tribeId;

    private String name;
    private String description;

    // Getters and Setters
}
