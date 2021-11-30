package com.interview.pwc.countryrouter.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public  class CountryInfoService {

    private static final Logger log = LoggerFactory.getLogger(CountryInfoService.class);
    private final ResourceLoader resourceLoader;

    @Value("${countryJsonPath}")
    private String countryJsonPath;

    public CountryInfoService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void readCountriesFromJson() throws IOException{
        File countryFile = this.resourceLoader.getResource(countryJsonPath).getFile();
        List<String> lines = Files.readAllLines(countryFile.toPath());
        log.info(lines.get(2));
    }
    
}
