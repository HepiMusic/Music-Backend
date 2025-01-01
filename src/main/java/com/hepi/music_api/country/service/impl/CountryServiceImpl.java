package com.hepi.music_api.country.service.impl;

import com.hepi.music_api.country.Country;
import com.hepi.music_api.country.dto.CountryDTO;
import com.hepi.music_api.country.repository.CountryRepository;
import com.hepi.music_api.country.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country createCountry(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setCode(countryDTO.getCode());
        country.setRegion(countryDTO.getRegion());

        Country savedCountry = countryRepository.save(country);
        return savedCountry;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();

    }

    @Override
    public Country getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found with id: " + id));
        return country;
    }

    @Override
    public Country updateCountry(Long id, CountryDTO countryDTO) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found with id: " + id));

        country.setName(countryDTO.getName());
        country.setCode(countryDTO.getCode());
        country.setRegion(countryDTO.getRegion());

        Country updatedCountry = countryRepository.save(country);
        return updatedCountry;
    }

    @Override
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found with id: " + id));
        countryRepository.delete(country);
    }

}
