package com.hepi.music_api.songs.repository;

import com.hepi.music_api.songs.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtistId(Long artistId);
    List<Song> findByGenreId(Long genreId);
    List<Song> findByCountryId(Long countryId);
}
