package com.hepi.music_api.country.service;

import com.hepi.music_api.country.model.Country;
import com.hepi.music_api.country.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    Country createCountry(CountryDTO countryDto);
    List<Country> getAllCountries();
    Country getCountryById(Long id);
    Country updateCountry(Long id, CountryDTO countryDto);
    void deleteCountry(Long id);
}
