package com.hmllc.explorecalijpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmllc.explorecalijpa.model.TourRating;

public interface TourRatingRepository extends JpaRepository<TourRating, Integer>{

    Optional<TourRating> findByTourIdAndCustomerId(Integer tourId, Integer customerId);
    
    List<TourRating> findByTourIdOrderByScoreDesc(Integer tourId);

}
