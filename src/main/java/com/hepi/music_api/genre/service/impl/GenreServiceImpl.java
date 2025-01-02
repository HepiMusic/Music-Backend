package com.hepi.music_api.genre.service.impl;

import com.hepi.music_api.exception.ResourceNotFoundException;
import com.hepi.music_api.genre.model.Genre;
import com.hepi.music_api.genre.dto.GenreDTO;
import com.hepi.music_api.genre.repository.GenreRepository;
import com.hepi.music_api.genre.service.GenreService;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.songs.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final SongRepository songRepository;

    @Override
    public Genre addGenre(GenreDTO genreDTO) {
        if (genreRepository.findByName(genreDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("Genre with name " + genreDTO.getName() + " already exists");
        }

        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());
        genre.setThumbnailUrl(genreDTO.getThumbnailUrl());
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long genreId, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));

        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());
        genre.setThumbnailUrl(genreDTO.getThumbnailUrl());

        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long genreId) {
        if (!genreRepository.existsById(genreId)) {
            throw new ResourceNotFoundException("Genre not found with ID: " + genreId);
        }

        genreRepository.deleteById(genreId);
    }

    @Override
    public Genre getGenreDetails(Long genreId) {
        return genreRepository.findByGenreId(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with ID: " + genreId));
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Song> getSongsByGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));

        // Update the popularity score based on the number of songs
        genre.setPopularityScore(genre.getSongs().size());
        genreRepository.save(genre);

        return genre.getSongs();
    }
    @Override
    public List<Song> filterSongsByGenre(Long genreId) {
        return songRepository.findAll().stream()
                .filter(song -> song.getGenre() != null && song.getGenre().getGenreId().equals(genreId))
                .collect(Collectors.toList());
    }


}
