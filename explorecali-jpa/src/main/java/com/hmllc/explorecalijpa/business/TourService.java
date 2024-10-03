package com.hmllc.explorecalijpa.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmllc.explorecalijpa.model.DifficultyLevel;
import com.hmllc.explorecalijpa.model.Region;
import com.hmllc.explorecalijpa.model.Tour;
import com.hmllc.explorecalijpa.model.TourPackage;
import com.hmllc.explorecalijpa.repository.TourRepository;

@Service
public class TourService {

    private TourRepository tourRepository;

    private TourPackageService tourPackageService;

    public TourService(TourRepository tourRepository, TourPackageService tourPackageService) {
	super();
	this.tourRepository = tourRepository;
	this.tourPackageService = tourPackageService;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration,
	    String bullets, String keywords, String tourPackageName, DifficultyLevel difficulty, Region region) {
	TourPackage tourPackage = tourPackageService.findByName(tourPackageName);
	Tour tour = new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficulty,
		region);
	return tourRepository.save(tour);
    }

    public List<Tour> getAllTours() {
	return tourRepository.findAll();
    }

    public List<Tour> findByDifficulty(String difficulty) {
	return tourRepository.findByDifficulty(DifficultyLevel.valueOf(difficulty));
    }

    public List<Tour> findByPackage(String packageName) {
	return tourRepository.findByTourPackageCode(packageName);
    }

}
