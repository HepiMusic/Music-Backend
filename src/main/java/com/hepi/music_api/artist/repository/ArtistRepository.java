package com.hepi.music_api.artist.repository;

import com.hepi.music_api.artist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByCountryId(Long countryId);
    Optional<Artist> findByName(String name);
    Optional<Artist> findByArtistId(Long artistId);
}
