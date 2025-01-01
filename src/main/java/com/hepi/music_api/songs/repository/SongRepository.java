package com.hepi.music_api.songs.repository;

import com.hepi.music_api.songs.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByCategoryId(Long categoryId);

    Optional<Song> findBySongId(Long SongId);
    List<Song> findByCountryId(Long countryId);
    List<Song> findByArtistId(Long artistId);
    List<Song> findByStatus(String status); // e.g., "Most Favorable"
}
