package com.hepi.music_api.artist.controller;

import com.hepi.music_api.artist.dto.ArtistDTO;
import com.hepi.music_api.artist.model.Artist;
import com.hepi.music_api.artist.service.ArtistService;
import com.hepi.music_api.songs.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody ArtistDTO artistDTO) {
        Artist artist = artistService.createArtist(artistDTO);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
        Artist artist = artistService.updateArtist(id, artistDTO);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = artistService.getAllArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getSongsByArtist(@PathVariable Long id) {
        List<Song> songs = artistService.getSongsByArtist(id);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
