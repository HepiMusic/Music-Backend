package com.hepi.music_api.songs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private Long id;
    private String title;
    private String description;
    private String filePath;
    private String thumbnailPath;
    private Long artistId;
    private Long genreId;
    private Long countryId;
    private Long tribeId;

}
