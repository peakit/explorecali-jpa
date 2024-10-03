/**
 * 
 */
package com.hmllc.explorecalijpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmllc.explorecalijpa.model.TourPackage;

public interface TourPackageRepository extends JpaRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String name);
}
