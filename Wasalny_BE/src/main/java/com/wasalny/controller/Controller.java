package com.wasalny.controller;

import com.wasalny.entities.Station;
import com.wasalny.entities.Trip;
import com.wasalny.repository.stationRepository;
import com.wasalny.repository.tripRepository;
import com.wasalny.repository.userRepository;
import com.wasalny.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api")

@Configuration
@EnableWebMvc
public class Controller extends WebMvcConfigurerAdapter {

    private boolean signedIn = false;
    @Autowired
    stationRepository SR;
    @Autowired
    tripRepository TR;
    @Autowired
    userRepository UR;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200",
                "http://frontend12-trip2-scheduling-app.apps.eu410.prod.nextcle.com/").allowedMethods("POST");
    }

    @PostMapping("/stations")
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        if (signedIn) {
            try {
                Station _station = SR
                        .save(new Station(station.getName()));
                return new ResponseEntity<>(_station, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/stations/{id}")
    public ResponseEntity<Station> editStation(@PathVariable("id") int id, @RequestBody Station station) {
        if (signedIn) {
            Optional<Station> stationData = SR.findById(id);
            if (stationData.isPresent()) {
                Station _station = stationData.get();
                _station.setName(station.getName());
                return new ResponseEntity<>(SR.save(_station), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> listStations() {
        if (signedIn) {
            try {
                List stations = new ArrayList<Station>();
                SR.findAll().forEach(stations::add);
                if (stations.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(stations, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/stations/{id}")
    public ResponseEntity<Station> findStationById(@PathVariable("id") int id) {
        if (signedIn) {
            Optional<Station> stationData = SR.findById(id);
            if (stationData.isPresent()) {
                return new ResponseEntity<>(stationData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }

    public Station getStationByName(String stationName) {
        List<Station> stations = SR.findStationByName(stationName);
        return stations.get(0);
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity<HttpStatus> deleteStation(@PathVariable("id") int id) {
        if (signedIn) {
            try {
                SR.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    /////////////////////////////////////////////

    @PostMapping("/trips")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        if (signedIn) {
            try {
                Station _fromStation = getStationByName(trip.getStartStation().getName());
                Station _toStation = getStationByName(trip.getEndStation().getName());
                Trip _trip = TR
                        .save(new Trip(
                                _fromStation,
                                _toStation,
                                trip.getStartTime(),
                                trip.getEndTime()));
                return new ResponseEntity<>(_trip, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<Trip> editTrip(@PathVariable("id") int id, @RequestBody Trip trip) {
        if (signedIn) {
            Optional<Trip> tripData = TR.findById(id);
            if (tripData.isPresent()) {
                Station _fromStation = getStationByName(trip.getStartStation().getName());
                Station _toStation = getStationByName(trip.getEndStation().getName());
                Trip _trip = tripData.get();
                _trip.setStartStation(_fromStation);
                _trip.setEndStation(_toStation);
                _trip.setStartTime(trip.getStartTime());
                _trip.setEndTime(trip.getEndTime());
                return new ResponseEntity<>(TR.save(_trip), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> listTrips() {
        if (signedIn) {
            try {
                List trips = new ArrayList<Trip>();
                TR.findAll().forEach(trips::add);
                if (trips.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(trips, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<Trip> findTripById(@PathVariable("id") int id) {
        if (signedIn) {
            Optional<Trip> tripData = TR.findById(id);
            if (tripData.isPresent()) {
                return new ResponseEntity<>(tripData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<HttpStatus> deleteTrip(@PathVariable("id") int id) {
        if (signedIn) {
            try {
                TR.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    /////////////////////////////////////////////

    @PostMapping("/user/signup")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User _user = UR
                    .save(new User(
                            user.getUsername(),
                            user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @CrossOrigin(origins = "http://frontend12-trip2-scheduling-app.apps.eu410.prod.nextcle.com")
    @PostMapping("/user/login")
    public ResponseEntity<HttpStatus> signIn(@RequestBody User user) {
        try {
            Optional<User> userData = Optional.ofNullable(UR.findByUsername(user.getUsername()).get(0));
            if (userData.isPresent()) {
                if (Objects.equals(user.getPassword(), userData.get().getPassword())) {
                    signedIn = true;
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/logout")
    public ResponseEntity<HttpStatus> signOut() {
        signedIn = false;
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
