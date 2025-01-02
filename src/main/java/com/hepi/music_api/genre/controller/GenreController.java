package com.hepi.music_api.genre.controller;

import com.hepi.music_api.genre.model.Genre;
import com.hepi.music_api.genre.dto.GenreDTO;
import com.hepi.music_api.genre.service.GenreService;
import com.hepi.music_api.songs.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody GenreDTO genreDTO) {
        Genre genre = genreService.addGenre(genreDTO);
        return new ResponseEntity<>(genre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        Genre genre = genreService.updateGenre(id, genreDTO);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long genreId) {
        Genre genre = genreService.getGenreDetails(genreId);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getSongsByGenre(@PathVariable Long id) {
        List<Song> songs = genreService.getSongsByGenre(id);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}/filter-songs")
    public ResponseEntity<List<Song>> filterSongsByGenre(@PathVariable Long id) {
        List<Song> songs = genreService.filterSongsByGenre(id);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
