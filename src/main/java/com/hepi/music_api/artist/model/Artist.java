package com.hepi.music_api.artist.model;

import com.hepi.music_api.country.Country;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.tribe.Tribe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    @Column(nullable = false)
    private String name; // Artist name

    @Column(length = 500)
    private String biography; // Short bio

    @Column(name = "thumbnail_url")
    private String thumbnailUrl; // URL for artist's image

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country; // Reference to Country entity

    @ManyToOne
    @JoinColumn(name = "tribe_id")
    private Tribe tribe; // Reference to Tribe entity (optional)

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();


}
