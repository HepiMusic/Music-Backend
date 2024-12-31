package com.hepi.music_api.songs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private String title;
    private String description;
    private String thumbnail;
    private Long artistId;  // Reference to the artist
    private Long categoryId;
    private Long countryId;
}
