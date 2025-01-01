package com.hepi.music_api.songs.controller;

import com.hepi.music_api.songs.dto.SongDTO;
import com.hepi.music_api.songs.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(TT.getAllSongs());
    }

    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody SongDTO songDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TT.addSong(songDTO));
    }

    @PutMapping("/{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable Long songId, @RequestBody SongDTO songDTO) {
        return ResponseEntity.ok(TT.updateSong(songId, songDTO));
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long songId) {
        TT.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{songId}")
    public ResponseEntity<Song> getSongById(@PathVariable Long songId) {
        return ResponseEntity.ok(TT.getSongById(songId));
    }
}
