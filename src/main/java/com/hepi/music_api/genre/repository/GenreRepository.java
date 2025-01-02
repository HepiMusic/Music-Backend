package com.hepi.music_api.genre.repository;

import com.hepi.music_api.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
    Optional<Genre> findByGenreId(Long genreId);
}
