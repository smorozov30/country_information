package ru.smorozov30.country_information.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.smorozov30.country_information.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountriesRepositoryImpl extends CrudRepository<Country, Integer> {

    Optional<Country> findCountryByName(String name);

    @Query(value = "SELECT * FROM country WHERE id IN (" +
                         "SELECT country_id " +
                         "FROM domains " +
                         "WHERE domain LIKE %:domain%)",
            nativeQuery = true)
    List<Country> findCountriesByDomain(@Param("domain") String domain);
}