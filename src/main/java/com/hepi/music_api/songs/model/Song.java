package com.hepi.music_api.songs.model;

import com.hepi.music_api.artist.Artist;
import com.hepi.music_api.genre.Genre;
import com.hepi.music_api.songs.enums.SongStatus;
import com.hepi.music_api.tribe.Tribe;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    private String title;
    private String filePath; // File URL or storage path
    private String thumbnailImage;

    @Enumerated(EnumType.STRING)
    private SongStatus status;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "tribe_id", nullable = false)
    private Tribe tribe;


}
