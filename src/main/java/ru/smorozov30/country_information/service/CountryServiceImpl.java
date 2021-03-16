package ru.smorozov30.country_information.service;

import org.springframework.stereotype.Service;
import ru.smorozov30.country_information.domain.Country;
import ru.smorozov30.country_information.repository.CountriesRepositoryImpl;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountriesRepositoryImpl countriesRepository;

    public CountryServiceImpl(CountriesRepositoryImpl countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public List<Country> findAll() {
        return (List<Country>) countriesRepository.findAll();
    }

    @Override
    public Country findCountryByName(String name) {
        return countriesRepository.findCountryByName(name).orElse(new Country());
    }

    @Override
    public List<Country> findCountriesByDomain(String domain) {
        return countriesRepository.findCountriesByDomain(domain);
    }

    @Override
    public void reloadData() {
        countriesRepository.deleteAll();
    }
}
