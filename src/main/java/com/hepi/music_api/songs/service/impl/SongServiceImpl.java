package com.hepi.music_api.songs.service.impl;

import com.hepi.music_api.artist.Artist;
import com.hepi.music_api.artist.repository.ArtistRepository;
import com.hepi.music_api.country.repository.CountryRepository;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.songs.repository.SongRepository;
import com.hepi.music_api.songs.service.SongService;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository, 
                           CategoryRepository categoryRepository, CountryRepository countryRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.categoryRepository = categoryRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song addSong(SongDTO songDTO) {
        Artist artist = artistRepository.findById(songDTO.getArtistId())
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + songDTO.getArtistId()));
        Category category = categoryRepository.findById(songDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + songDTO.getCategoryId()));
        Country country = countryRepository.findById(songDTO.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + songDTO.getCountryId()));

        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setDescription(songDTO.getDescription());
        song.setThumbnail(songDTO.getThumbnail());
        song.setArtist(artist);
        song.setCategory(category);
        song.setCountry(country);

        return songRepository.save(song);
    }

    @Override
    public Song updateSong(Long songId, SongDTO songDTO) {
        Song existingSong = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + songId));

        Artist artist = artistRepository.findById(songDTO.getArtistId())
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + songDTO.getArtistId()));
        Category category = categoryRepository.findById(songDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + songDTO.getCategoryId()));
        Country country = countryRepository.findById(songDTO.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + songDTO.getCountryId()));

        existingSong.setTitle(songDTO.getTitle());
        existingSong.setDescription(songDTO.getDescription());
        existingSong.setThumbnail(songDTO.getThumbnail());
        existingSong.setArtist(artist);
        existingSong.setCategory(category);
        existingSong.setCountry(country);

        return songRepository.save(existingSong);
    }

    @Override
    public void deleteSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + songId));
        songRepository.delete(song);
    }

    @Override
    public Song getSongById(Long songId) {
        return songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + songId));
    }
}
