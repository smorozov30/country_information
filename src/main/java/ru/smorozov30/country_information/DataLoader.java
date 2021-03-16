package ru.smorozov30.country_information;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.smorozov30.country_information.domain.Country;
import ru.smorozov30.country_information.repository.CountriesRepositoryImpl;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {
    private static final String ALL_COUNTRY_API = "https://restcountries.eu/rest/v2/all";

    @Autowired
    private RestTemplate rest;
    private CountriesRepositoryImpl countriesRepository;

    @Autowired
    public DataLoader(CountriesRepositoryImpl countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @PostConstruct
    public void load() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String countriesJSON = rest.getForObject(ALL_COUNTRY_API, String.class);
        Country[] countries = gson.fromJson(countriesJSON, Country[].class);
        for (Country country : countries) {
            countriesRepository.save(country);
        }
    }
}
