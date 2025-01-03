package com.hepi.music_api.tribe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TribeDTO {

    private Long id;
    private String name;
    private String description;
    private Long countryId; // Reference to the country
}
