package ru.smorozov30.country_information.service;

import ru.smorozov30.country_information.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();
    Country findCountryByName(String name);
    List<Country> findCountriesByDomain(String domain);
    void reloadData();
}
