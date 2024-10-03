/**
 * 
 */
package com.hmllc.explorecalijpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmllc.explorecalijpa.model.DifficultyLevel;
import com.hmllc.explorecalijpa.model.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByDifficulty(DifficultyLevel difficulty);
    
    List<Tour> findByTourPackageCode(String packageName);
}
