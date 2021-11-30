package com.interview.pwc.countryrouter.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public  class CountryInfoService {

    private static final Logger log = LoggerFactory.getLogger(CountryInfoService.class);
    private final ResourceLoader resourceLoader;

    private Map<String, Country> countries = new HashMap<>();
 
    @Value("${countryJsonPath}")
    private String countryJsonPath;

    public CountryInfoService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void readCountriesFromJson() throws IOException{
        File countryFile = this.resourceLoader.getResource(countryJsonPath).getFile();
        ObjectMapper mapper = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Country> countries = mapper.readValue(countryFile, new TypeReference<List<Country>>(){}).stream().collect(
            Collectors.toMap(Country::getCca3, Function.identity()));

        log.info("Map contains currently {} countries", countries.size() );

    }

    public Map<String, Country> getCountryMap(){
        return this.countries;
    }
    
}
