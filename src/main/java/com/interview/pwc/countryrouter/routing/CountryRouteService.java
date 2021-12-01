package com.interview.pwc.countryrouter.routing;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.interview.pwc.countryrouter.input.Country;
import com.interview.pwc.countryrouter.input.CountryInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.interview.pwc.countryrouter.routing.graph.BellmanFord;
import  com.interview.pwc.countryrouter.routing.graph.Graph;
import com.interview.pwc.countryrouter.routing.graph.model.IGraph;

@Service
public class CountryRouteService {

    private static final Logger log = LoggerFactory.getLogger(CountryInfoService.class);
    private final CountryInfoService countryInfoService;

    CountryRouteService(CountryInfoService countryInfoService){
        this.countryInfoService = countryInfoService;
    }


    public CountryDTO traverseCountryBorderPath(String source, String destination){
        Map<String, Country> countryMap = this.countryInfoService.getCountryMap();
        if( countryMap.get(source) == null || countryMap.get(destination) == null ){
            throw new IllegalArgumentException("Destination or source country does not exist!");
        }
        Map<String, IGraph<Country>>countryVertices = countryMap.values()
        .stream()
        .map(country -> new Graph(country))
        .collect(Collectors.toMap(graph -> graph.getData().getCca3(), graph -> graph));

        countryVertices.forEach((String k, IGraph<Country> v) -> {
            this.countryInfoService.setupEdgesForGraph(v, countryVertices);
        });

        IGraph<Country> startVertex = countryVertices.get(source);
        IGraph<Country> destinationVertex = countryVertices.get(destination);
        BellmanFord bfSearch = new BellmanFord(startVertex, destinationVertex);
        List<IGraph<Country>> traversedCountries = bfSearch.startSearch(countryVertices);
        CountryDTO result =  new CountryDTO();
        result.setRoute(traversedCountries.stream()
                        .map(tc -> tc.getData().getCca3())
                        .collect(Collectors.toList()));
        return result;
    }

    
}
