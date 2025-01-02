package com.hepi.music_api.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDTO {

    private Long id;
    private String name;
    private String biography;
    private String thumbnailUrl; // Artist's image URL
    private Long countryId; // Country reference
    private Long tribeId; // Tribe reference (optional)
}
