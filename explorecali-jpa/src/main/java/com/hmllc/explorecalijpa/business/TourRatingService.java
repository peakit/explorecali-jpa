package com.hmllc.explorecalijpa.business;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hmllc.explorecalijpa.model.Tour;
import com.hmllc.explorecalijpa.model.TourRating;
import com.hmllc.explorecalijpa.repository.TourRatingRepository;

@Service
public class TourRatingService {

    private TourRatingRepository tourRatingRepository;

    private TourService tourService;

    public TourRatingService(TourRatingRepository tourRatingRepository, TourService tourService) {
	super();
	this.tourRatingRepository = tourRatingRepository;
	this.tourService = tourService;
    }

    public TourRating createNewRating(Integer tourId, Integer customerId, Integer score, String comments) {
	Tour tour = tourService.findById(tourId);
	TourRating tourRating = new TourRating(tour, customerId, score, comments);
	return tourRatingRepository.save(tourRating);
    }

    public TourRating findTourRatingById(Integer tourRatingId) {
	return tourRatingRepository.findById(tourRatingId)
		.orElseThrow(() -> new NoSuchElementException("No tour rating exists with id: " + tourRatingId));
    }

    public TourRating findRatingByTourIdAndCustomerId(Integer tourId, Integer customerId) {
	return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId)
		.orElseThrow(() -> new NoSuchElementException(
			"No such rating exists with tour id: " + tourId + " and customer id: " + customerId));
    }

    public List<TourRating> findAllRatingsByTourId(Integer tourId) {
	return tourRatingRepository.findByTourIdOrderByScoreDesc(tourId);
    }

    public TourRating updateTourRating(Integer tourId, Integer customerId, Integer score, String comments) {
	TourRating existingRating = findRatingByTourIdAndCustomerId(tourId, customerId);
	existingRating.setScore(score);
	existingRating.setComments(comments);
	return tourRatingRepository.save(existingRating);
    }

    public TourRating updateTourRating(Integer tourId, Integer customerId, Optional<Integer> score,
	    Optional<String> comments) {
	TourRating existingRating = findRatingByTourIdAndCustomerId(tourId, customerId);
	score.ifPresent((s) -> existingRating.setScore(s));
	comments.ifPresent((c) -> existingRating.setComments(c));
	return tourRatingRepository.save(existingRating);
    }

    public void deleteTourRating(Integer tourRatingId) {
	tourRatingRepository.deleteById(tourRatingId);
    }

    public void deleteTourRatingByTourIdAndCustomerId(Integer tourId, Integer customerId) {
	TourRating rating = findRatingByTourIdAndCustomerId(tourId, customerId);
	deleteTourRating(rating.getId());
    }

    public Double getAverageScore(Integer tourId) {
	List<TourRating> tourRatings = tourRatingRepository.findByTourIdOrderByScoreDesc(tourId);
	return tourRatings.stream().collect(Collectors.averagingInt(TourRating::getScore));
    }

}
