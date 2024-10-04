package com.hmllc.explorecalijpa.web;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hmllc.explorecalijpa.business.TourRatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/tours/{id}/ratings")
public class TourRatingController {

    private TourRatingService tourRatingService;

    public TourRatingController(TourRatingService tourRatingService) {
	super();
	this.tourRatingService = tourRatingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "id") Integer tourId, @RequestBody @Valid RatingDto ratingDto) {
	tourRatingService.createNewRating(tourId, ratingDto.getCustomerId(), ratingDto.getScore(),
		ratingDto.getComments());
    }

    @GetMapping(value = "/average")
    @ResponseStatus(HttpStatus.OK)
    public Double getAverageScoreForTour(@PathVariable(value = "id") Integer tourId) {
	return tourRatingService.getAverageScore(tourId);
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForTour(Integer tourId) {
	return tourRatingService.findAllRatingsByTourId(tourId).stream().map(RatingDto::new).toList();
    }

    @PutMapping
    public RatingDto updateRating(@PathVariable(value = "id") Integer tourId, @RequestBody @Valid RatingDto ratingDto) {
	return new RatingDto(tourRatingService.updateTourRating(tourId, ratingDto.getCustomerId(), ratingDto.getScore(),
		ratingDto.getComments()));
    }

    @PatchMapping
    public RatingDto updateForProvidedFieldsOnly(@PathVariable(value = "id") Integer tourId,
	    @RequestBody @Valid RatingDto ratingDto) {
	return new RatingDto(tourRatingService.updateTourRating(tourId, ratingDto.getCustomerId(),
		Optional.ofNullable(ratingDto.getScore()), Optional.ofNullable(ratingDto.getComments())));
    }

    @DeleteMapping("{customerId}")
    public void deletingRating(@PathVariable(value = "id") Integer tourId,
	    @PathVariable(value = "customerId") Integer customerId) {
	tourRatingService.deleteTourRatingByTourIdAndCustomerId(tourId, customerId);
    }

    @ExceptionHandler(value = { NoSuchElementException.class, MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String return404(Exception exception) {
	return exception.getMessage();
    }
}
