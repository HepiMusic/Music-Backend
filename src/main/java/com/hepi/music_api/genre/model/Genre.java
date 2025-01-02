package com.hepi.music_api.genre.model;

import com.hepi.music_api.songs.model.Song;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    @Column(nullable = false, unique = true)
    private String name; // e.g., "Gospel", "Reggae"

    @Column(length = 500)
    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl; // URL for the genre's icon/image

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();

    @Column(name = "popularity_score", nullable = false)
    private int popularityScore = 0; // Based on song count or user engagement



}
