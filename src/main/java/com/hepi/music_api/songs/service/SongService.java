package com.hepi.music_api.songs.service;

import com.hepi.music_api.songs.dto.SongDTO;
import com.hepi.music_api.songs.model.Song;

import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
    Song addSong(SongDTO songDTO);
    Song updateSong(Long songId, SongDTO songDTO);
    void deleteSong(Long songId);
    Song getSongById(Long songId);
}
