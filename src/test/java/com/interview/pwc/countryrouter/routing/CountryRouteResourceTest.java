package com.interview.pwc.countryrouter.routing;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CountryRouteResourceTest {

    @Autowired
    private MockMvc mockMvc;
  
    @Autowired
    private ObjectMapper objectMapper;

    private final List<String> EXPECTED_CLOSE_COUNTRIES_RESULT  = Arrays.asList("CZE","AUT","ITA");

    private final List<String> EXPECTED_FAR_COUNTRIES_RESULT  = Arrays.asList("AUT","HUN","ROU","BGR","TUR");

    @Test
    void routeTraverseWorksForCloseBorderedCountries() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
            get("/api/routing/CZE/ITA")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn().getResponse();
        
        CountryDTO result = objectMapper.readValue( response.getContentAsString(), CountryDTO.class);
        assertThat(result.getRoute().equals(EXPECTED_CLOSE_COUNTRIES_RESULT)).isTrue();
    }

    @Test
    void routeTraverseWorksForFarBorderedCountries() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
            get("/api/routing/AUT/TUR")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn().getResponse();
        
        CountryDTO result = objectMapper.readValue( response.getContentAsString(), CountryDTO.class);
        assertThat(result.getRoute().equals(EXPECTED_FAR_COUNTRIES_RESULT)).isTrue();
    }

    @Test
    void routeTraverseShouldFailForSeaAndLandBorderedCountries() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
            get("/api/routing/AUT/FJI")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andReturn().getResponse();
    }

    @Test
    void routeTraverseShouldFailForInvalidSourceOrDestinationCountries() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
            get("/api/routing/AUTO/ITA")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andReturn().getResponse();
    }
    
    
}
