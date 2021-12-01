package com.interview.pwc.countryrouter.input;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryInfoServiceTest {

    @Autowired
	private CountryInfoService countryInfoService;


    @Test
	public void countriesLoadFromJSON() throws Exception {
		assertThat(countryInfoService.getCountryMap().isEmpty()).isFalse();
	}
    
}
