package com.hepi.music_api.songs.service.impl;

import com.hepi.music_api.artist.model.Artist;
import com.hepi.music_api.artist.repository.ArtistRepository;
import com.hepi.music_api.country.model.Country;
import com.hepi.music_api.country.repository.CountryRepository;
import com.hepi.music_api.exception.ResourceNotFoundException;
import com.hepi.music_api.genre.model.Genre;
import com.hepi.music_api.genre.repository.GenreRepository;
import com.hepi.music_api.songs.dto.SongDTO;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.songs.repository.SongRepository;
import com.hepi.music_api.songs.service.SongService;
import com.hepi.music_api.tribe.Tribe;
import com.hepi.music_api.tribe.repository.TribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final TribeRepository tribeRepository;

    @Override
    public Song addSong(SongDTO songDTO) {
        Artist artist = artistRepository.findByArtistId(songDTO.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with ID: " + songDTO.getArtistId()));
        Genre genre = genreRepository.findByGenreId(songDTO.getGenreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with ID: " + songDTO.getGenreId()));
        Country country = countryRepository.findByCountryId(songDTO.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with ID: " + songDTO.getCountryId()));
        Tribe tribe = null;
        if (songDTO.getTribeId() != null) {
            tribe = tribeRepository.findById(songDTO.getTribeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tribe not found with ID: " + songDTO.getTribeId()));
        }

        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setDescription(songDTO.getDescription());
        song.setFilePath(songDTO.getFilePath());
        song.setThumbnailPath(songDTO.getThumbnailPath());
        song.setArtist(artist);
        song.setGenre(genre);
        song.setCountry(country);
        song.setTribe(tribe);

        return songRepository.save(song);
    }

    @Override
    public Song updateSong(Long songId, SongDTO songDTO) {
        Song song = songRepository.findBySongId(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found with ID: " + songId));

        Artist artist = artistRepository.findByArtistId(songDTO.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with ID: " + songDTO.getArtistId()));
        Genre genre = genreRepository.findByGenreId(songDTO.getGenreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with ID: " + songDTO.getGenreId()));
        Country country = countryRepository.findByCountryId(songDTO.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with ID: " + songDTO.getCountryId()));
        Tribe tribe = null;
        if (songDTO.getTribeId() != null) {
            tribe = tribeRepository.findByTribeId(songDTO.getTribeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tribe not found with ID: " + songDTO.getTribeId()));
        }

        song.setTitle(songDTO.getTitle());
        song.setDescription(songDTO.getDescription());
        song.setFilePath(songDTO.getFilePath());
        song.setThumbnailPath(songDTO.getThumbnailPath());
        song.setArtist(artist);
        song.setGenre(genre);
        song.setCountry(country);
        song.setTribe(tribe);

        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Long songId) {
        if (!songRepository.existsBySongId(songId)) {
            throw new ResourceNotFoundException("Song not found with ID: " + songId);
        }
        songRepository.deleteById(songId);
    }

    @Override
    public Song getSongDetails(Long songId) {
        return songRepository.findBySongId(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found with ID: " + songId));
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSongsByArtist(Long artistId) {
        return songRepository.findAllByArtist_ArtistId(artistId);
    }

    @Override
    public List<Song> getSongsByGenre(Long genreId) {
        return songRepository.findAllByGenre_GenreId(genreId);
    }

    @Override
    public List<Song> getSongsByCountry(Long countryId) {
        return songRepository.findAllByCountry_CountryId(countryId);
    }
}
