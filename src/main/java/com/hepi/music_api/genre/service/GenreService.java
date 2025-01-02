package com.hepi.music_api.genre.service;

import com.hepi.music_api.genre.Genre;
import com.hepi.music_api.genre.dto.GenreDTO;
import com.hepi.music_api.songs.model.Song;

import java.util.List;

public interface GenreService {

    Genre addGenre(GenreDTO genreDTO);
    Genre updateGenre(Long genreId, GenreDTO genreDTO);
    void deleteGenre(Long genreId);
    Genre getGenreDetails(Long genreId);
    List<Genre> getAllGenres();
    List<Song> getSongsByGenre(Long genreId);
}
