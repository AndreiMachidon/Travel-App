package com.springflutter.demo.controller;

import com.springflutter.demo.dto.FlightForm;
import com.springflutter.demo.entity.Flight;
import com.springflutter.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping(path = "flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(path = "/search")
    private HashSet<Flight> searchFlights(@RequestBody FlightForm form){
        return flightService.searchFlights(form.getDate(), form.getMaximumPrice());
    }

}
