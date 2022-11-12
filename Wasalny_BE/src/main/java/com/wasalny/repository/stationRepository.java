package com.wasalny.repository;
import com.wasalny.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface stationRepository extends JpaRepository<Station, Integer> {
//
  @Query("SELECT s from Station s WHERE s.name = :name")
  List<Station> findStationByName(@Param("name") String name);
}
