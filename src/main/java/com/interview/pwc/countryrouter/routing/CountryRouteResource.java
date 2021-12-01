package com.interview.pwc.countryrouter.routing;

import java.util.List;
import java.util.Optional;

import com.interview.pwc.countryrouter.input.Country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Armin
 */
@RestController
@RequestMapping("/api")
public class CountryRouteResource {

    private Logger log = LoggerFactory.getLogger(CountryRouteResource.class);
    private final CountryRouteService countryRouteService;

    public CountryRouteResource(CountryRouteService countryRouteService){
        this.countryRouteService = countryRouteService;
    }

    @GetMapping("/routing/{source}/{destination}")
    ResponseEntity<CountryDTO> getTraversedCountries(@PathVariable String source,@PathVariable String destination){
        CountryDTO traversedCountries = this.countryRouteService.traverseCountryBorderPath(source, destination);
        return new ResponseEntity<CountryDTO>(traversedCountries, HttpStatus.OK);
    }
    
}
