package ru.smorozov30.country_information.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.smorozov30.country_information.CountryInformationApplication;
import ru.smorozov30.country_information.domain.Country;
import ru.smorozov30.country_information.service.CountryServiceImpl;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CountryInformationApplication.class)
@AutoConfigureMockMvc
class CountriesControllerTest {

    @MockBean
    private CountryServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findByName() throws Exception {
        Country country = new Country();
        country.setId(1);
        country.setName("Russia");

        when(service.findCountryByName(anyString())).thenReturn(country);

        MvcResult mvcResult = this.mockMvc.perform(get("/country/name/Russia"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1," +
                "\"name\":\"Russia\"," +
                "\"population\":0," +
                "\"flag\":null," +
                "\"topLevelDomain\":[]," +
                "\"latlng\":[]," +
                "\"currencies\":[]," +
                "\"languages\":[]," +
                "\"translations\":null," +
                "\"regionalBlocs\":[]}";

        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void findByDomain() throws Exception {
        Country country = new Country();
        country.setId(1);
        country.setName("Russia");
        country.setTopLevelDomain(new HashSet<String>(Arrays.asList(".af")){});

        when(service.findCountriesByDomain(anyString())).thenReturn(Arrays.asList(country));

        MvcResult mvcResult = this.mockMvc.perform(get("/country/domain/af"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":1," +
                "\"name\":\"Russia\"," +
                "\"population\":0," +
                "\"flag\":null," +
                "\"topLevelDomain\":[\".af\"]," +
                "\"latlng\":[]," +
                "\"currencies\":[]," +
                "\"languages\":[]," +
                "\"translations\":null," +
                "\"regionalBlocs\":[]}]";

        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }
}