package com.hepi.music_api.artist.service;

import com.hepi.music_api.artist.dto.ArtistDTO;
import com.hepi.music_api.artist.model.Artist;
import com.hepi.music_api.songs.model.Song;

import java.util.List;

public interface ArtistService {

    Artist createArtist(ArtistDTO artistDTO);

    Artist updateArtist(Long artistId, ArtistDTO artistDTO);

    void deleteArtist(Long artistId);

    Artist getArtistById(Long artistId);

    List<Artist> getAllArtists();

    List<Song> getSongsByArtist(Long artistId);
}
