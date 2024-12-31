package com.hepi.music_api.country.dto;

import lombok.Data;

@Data
public class CountryDTO {
    private Long id;
    private String name;
    private String code;
    private String region;

    // Getters and Setters
}
