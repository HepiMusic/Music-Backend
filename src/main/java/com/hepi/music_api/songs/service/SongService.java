package com.hepi.music_api.songs.service;

import com.hepi.music_api.songs.dto.SongDTO;
import com.hepi.music_api.songs.model.Song;

import java.util.List;

public interface SongService {
    Song addSong(SongDTO songDTO);
    Song updateSong(Long songId, SongDTO songDTO);
    void deleteSong(Long songId);
    Song getSongDetails(Long songId);
    List<Song> getAllSongs();
    List<Song> getSongsByArtist(Long artistId);
    List<Song> getSongsByGenre(Long genreId);
    List<Song> getSongsByCountry(Long countryId);
}
