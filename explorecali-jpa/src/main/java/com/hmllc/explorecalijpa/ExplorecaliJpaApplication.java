package com.hmllc.explorecalijpa;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmllc.explorecalijpa.business.TourPackageService;
import com.hmllc.explorecalijpa.business.TourService;
import com.hmllc.explorecalijpa.model.DifficultyLevel;
import com.hmllc.explorecalijpa.model.Region;

@SpringBootApplication
public class ExplorecaliJpaApplication implements CommandLineRunner {

    private static final String INIT_DATA_FILE = "InitData.json";

    @Autowired
    private TourPackageService tourPackageService;

    @Autowired
    private TourService tourService;

    public static void main(String[] args) {
	SpringApplication.run(ExplorecaliJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	createAllTourPackages();
	System.out.println("Persisted all Tour Packages from InitData file.");
	createAllTours(INIT_DATA_FILE);
	System.out.println("Persisted all Tours from InitData file.");

    }

    private void createAllTourPackages() {
	tourPackageService.createTourPackage("BC", "Backpack Cal");
	tourPackageService.createTourPackage("CC", "California Calm");
	tourPackageService.createTourPackage("CH", "California Hot springs");
	tourPackageService.createTourPackage("CY", "Cycle California");
	tourPackageService.createTourPackage("DS", "From Desert to Sea");
	tourPackageService.createTourPackage("KC", "Kids California");
	tourPackageService.createTourPackage("NW", "Nature Watch");
	tourPackageService.createTourPackage("SC", "Snowboard Cali");
	tourPackageService.createTourPackage("TC", "Taste of California");
    }

    private void createAllTours(String initDataFile) throws StreamReadException, DatabindException, IOException {
	TourFileRecord.readFromFile(initDataFile)
		.forEach(r -> tourService.createTour(r.title(), r.description(), r.blurb(), r.price(), r.length(),
			r.bullets(), r.keywords(), r.packageName(), DifficultyLevel.valueOf(r.difficulty()),
			Region.findByLabel(r.region())));
    }

    record TourFileRecord(String packageName, String title, String blurb, String description, String bullets,
	    String difficulty, String length, Integer price, String region, String keywords) {

	static List<TourFileRecord> readFromFile(String initDataFile)
		throws StreamReadException, DatabindException, IOException {
	    return new ObjectMapper().readValue(new File(initDataFile), new TypeReference<List<TourFileRecord>>() {
	    });
	}
    }

}
