package com.hepi.music_api.artist.service.impl;

import com.hepi.music_api.artist.dto.ArtistDTO;
import com.hepi.music_api.artist.model.Artist;
import com.hepi.music_api.artist.repository.ArtistRepository;
import com.hepi.music_api.artist.service.ArtistService;
import com.hepi.music_api.country.Country;
import com.hepi.music_api.country.repository.CountryRepository;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.songs.repository.SongRepository;
import com.hepi.music_api.tribe.Tribe;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final CountryRepository countryRepository;
    private final TribeRepository tribeRepository;
    private final SongRepository songRepository;

    @Override
    public Artist createArtist(ArtistDTO artistDTO) {
        Country country = countryRepository.findById(artistDTO.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + artistDTO.getCountryId()));

        Tribe tribe = null;
        if (artistDTO.getTribeId() != null) {
            tribe = tribeRepository.find ById(artistDTO.getTribeId())
                    .orElseThrow(() -> new IllegalArgumentException("Tribe not found with ID: " + artistDTO.getTribeId()));
        }

        Artist artist = Artist.builder()
                .name(artistDTO.getName())
                .biography(artistDTO.getBiography())
                .thumbnailUrl(artistDTO.getThumbnailUrl())
                .country(country)
                .tribe(tribe)
                .build();

        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtist(Long artistId, ArtistDTO artistDTO) {
        Artist artist = artistRepository.findByArtistId(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + artistId));

        Country country = countryRepository.findById(artistDTO.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + artistDTO.getCountryId()));

        Tribe tribe = null;
        if (artistDTO.getTribeId() != null) {
            tribe = tribeRepository.findById(artistDTO.getTribeId())
                    .orElseThrow(() -> new IllegalArgumentException("Tribe not found with ID: " + artistDTO.getTribeId()));
        }

        artist.setName(artistDTO.getName());
        artist.setBiography(artistDTO.getBiography());
        artist.setThumbnailUrl(artistDTO.getThumbnailUrl());
        artist.setCountry(country);
        artist.setTribe(tribe);

        return artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + artistId));
        artistRepository.delete(artist);
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + artistId));
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public List<Song> getSongsByArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found with ID: " + artistId));
        return artist.getSongs();
    }
}
