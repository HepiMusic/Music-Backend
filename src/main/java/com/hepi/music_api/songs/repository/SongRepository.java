package com.hepi.music_api.songs.repository;

import com.hepi.music_api.songs.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByArtist_ArtistId(Long artistId);
    List<Song> findAllByGenre_GenreId(Long genreId);
    List<Song> findAllByCountry_CountryId(Long countryId);
    Optional<Song> findBySongId(Long songId);
    Boolean existsBySongId(Long songId);
}
