package ru.smorozov30.country_information;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.smorozov30.country_information.controller.CountriesController;
import ru.smorozov30.country_information.domain.Country;

import javax.sql.DataSource;

@SpringBootApplication
public class CountryInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryInformationApplication.class, args);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }


}
