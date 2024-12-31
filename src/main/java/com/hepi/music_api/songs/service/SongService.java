package com.hepi.music_api.songs.service;

import com.hepi.music_api.songs.model.Song;

import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
    Song addSong(Song song);
    Song updateSong(Long songId, Song songDetails);
    void deleteSong(Long songId);
    Song getSongById(Long songId);
}
