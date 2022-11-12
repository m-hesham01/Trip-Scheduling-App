package com.wasalny.repository;

import com.wasalny.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tripRepository extends JpaRepository<Trip,Integer> {

}
