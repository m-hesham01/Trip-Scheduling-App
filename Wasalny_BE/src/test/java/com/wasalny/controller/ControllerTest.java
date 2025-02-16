package com.wasalny.controller;

import com.wasalny.entities.Station;
import com.wasalny.repository.stationRepository;
import com.wasalny.repository.tripRepository;
import com.wasalny.repository.userRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Controller controller;

    @MockBean
    private stationRepository SR;

    @MockBean
    private tripRepository TR;

    @MockBean
    private userRepository UR;

    @Test
    void testAddStation_Unauthorized() throws Exception {
        setSignedIn(false);
        // Trying to add a station while signedIn = false
        String stationJson = "{\"name\": \"Cairo\"}";

        mockMvc.perform(post("/api/stations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stationJson))
                .andExpect(status().isUnauthorized());  // Expecting 401 UNAUTHORIZED
    }

        /**
     * Helper method to set the "signedIn" flag using reflection.
     */
    private void setSignedIn(boolean value) throws Exception {
        Field field = Controller.class.getDeclaredField("signedIn");
        field.setAccessible(true);
        field.set(controller, value);
    }

    @Test
    void testListStations_Authorized() throws Exception {
        setSignedIn(true);
        List<Station> stations = List.of(new Station("Cairo"), new Station("Alex"));
        when(SR.findAll()).thenReturn(stations);

        mockMvc.perform(get("/api/stations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))  // Expecting 2 stations
                .andExpect(jsonPath("$[0].name").value("Cairo"));
    }

    @Test
    void testFindStationById_NotFound() throws Exception {
        setSignedIn(true);
        when(SR.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/stations/1"))
                .andExpect(status().isNotFound()); // Expecting 404 NOT FOUND
    }
}
