package com.interview.pwc.countryrouter;
import static org.assertj.core.api.Assertions.assertThat;

import com.interview.pwc.countryrouter.routing.CountryRouteResource;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private CountryRouteResource resource;

	@Test
	public void contextLoads() throws Exception {
		assertThat(resource).isNotNull();
	}
}