package com.hmllc.explorecalijpa.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmllc.explorecalijpa.model.TourPackage;
import com.hmllc.explorecalijpa.repository.TourPackageRepository;

@Service
public class TourPackageService {

    private TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository) {
	super();
	this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
	TourPackage tourPackage = new TourPackage(code, name);
	return tourPackageRepository.save(tourPackage);
    }

    public List<TourPackage> getAllTourPackages() {
	return tourPackageRepository.findAll();
    }

    public long total() {
	return tourPackageRepository.count();
    }

    public TourPackage findByName(String name) {
	return tourPackageRepository.findByName(name)
		.orElseThrow(() -> new IllegalArgumentException("No tour package found with the name: " + name));
    }

}
