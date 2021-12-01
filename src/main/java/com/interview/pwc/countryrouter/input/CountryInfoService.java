package com.interview.pwc.countryrouter.input;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.pwc.countryrouter.routing.graph.Graph;
import com.interview.pwc.countryrouter.routing.graph.model.IGraph;
import com.interview.pwc.countryrouter.routing.graph.Edge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public  class CountryInfoService {

    private static final Logger log = LoggerFactory.getLogger(CountryInfoService.class);
    private final ResourceLoader resourceLoader;
    /**
     * Trail cost for traversal
     */
    private final Integer UNIVERSAL_COUNTRY_WEIGHT = 1;
    /**
     * Edges are defined from left to right
     */
    private final Integer UNIVERSAL_DIRECTION = 1;

    private Map<String, Country> countries = new HashMap<>();
 
    @Value("${countryJsonPath}")
    private String countryJsonPath;

    public CountryInfoService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    private void readCountriesFromJson() throws IOException{
        File countryFile = this.resourceLoader.getResource(countryJsonPath).getFile();
        ObjectMapper mapper = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Country> countries = mapper.readValue(countryFile, new TypeReference<List<Country>>(){}).stream().collect(
            Collectors.toMap(Country::getCca3, Function.identity()));

        log.info("Map contains currently {} countries", countries.size() );
        this.countries = countries;
    }

    public Map<String, Country> getCountryMap(){
        return this.countries;
    }

    public void setupEdgesForGraph(IGraph<Country> graph, Map<String, IGraph<Country>>countryVertices) {
        Country currentCountry = graph.getData();
        for(String countryCode: currentCountry.getBorders()){
            Edge e = new Edge(currentCountry.getLandlocked() ? UNIVERSAL_COUNTRY_WEIGHT:Integer.MAX_VALUE/2, (Graph) graph, UNIVERSAL_DIRECTION );
            Country borderCountry = this.countries.get(countryCode);
            e.setAdjected(countryVertices.get(borderCountry.getCca3()));
            graph.addEdge(e);
        }
    }
    
}
