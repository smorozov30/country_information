package ru.smorozov30.country_information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.smorozov30.country_information.DataLoader;
import ru.smorozov30.country_information.domain.Country;
import ru.smorozov30.country_information.service.CountryServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountriesController {

    private final CountryServiceImpl service;

    @Autowired
    private ApplicationContext context;

    public CountriesController(CountryServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Country> findAll() {
        return new ArrayList<>(service.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> findByName(@PathVariable String name) {
        Country country = service.findCountryByName(name);
        return new ResponseEntity<>(
                country,
                country.getId() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/domain/{domain}")
    public List<Country> findByDomain(@PathVariable String domain) {
        return new ArrayList<>(service.findCountriesByDomain(domain));
    }

    @DeleteMapping("/reload")
    public ResponseEntity<Void> delete() {
        service.reloadData();
        DataLoader loader = context.getBean(DataLoader.class);
        loader.load();
        return ResponseEntity.ok().build();
    }
}
