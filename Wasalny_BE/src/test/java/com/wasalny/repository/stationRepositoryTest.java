package com.wasalny.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wasalny.entities.Station;

@DataJpaTest // configures an in memory h2 databse
public class stationRepositoryTest {
    @Autowired
    private stationRepository stationRepo;

    @Test
    void testFindStationByName() {
        Station station1 = new Station("Giza");
        Station station2 = new Station("Cairo");
        stationRepo.save(station1);
        stationRepo.save(station2);

        List<Station> result = stationRepo.findStationByName("Cairo");

        assertFalse(result.isEmpty());
        assertEquals( result.get(0).getName(), "Cairo");
    }
}
