package com.springflutter.demo.service;

import com.springflutter.demo.dao.FlightDao;
import com.springflutter.demo.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

@Service
public class FlightService {

    @Autowired
    FlightDao flightDao;

    /**
     * This method finds a flight by the user's criteria
     */
    public HashSet<Flight> searchFlights(Date date, Double maximumPrice){

        HashSet<Flight> allFlights = new HashSet<>(flightDao.findAll());
        HashSet<Flight> availableFlights = new HashSet<>();

        for(Flight flight: allFlights){
            boolean ok = false;
            for(Date availableDate: flight.getFlightDates()) {
                if (availableDate.compareTo(date) == 0) {
                    ok = true;
                }
            }

            if(ok == true){
                if(flight.getTicketPrice() <= maximumPrice){
                    availableFlights.add(flight);
                }
            }
        }

        return availableFlights;

    }

    /**
     * This method populates the table with flights for test purposes
     */
    @PostConstruct
    private void initFlights(){

        Date[] dates1 ={ new Date(122, Calendar.JULY, 20), new Date(122, Calendar.AUGUST, 15)};

        flightDao.save(new Flight("Boeing 747", dates1, 200.00));

        Date[] dates2 = {new Date(122, Calendar.SEPTEMBER, 21),new Date(122, Calendar.JANUARY, 13)};

        flightDao.save(new Flight("Skymarks 568", dates2, 170.00));

        Date[] dates3 = { new Date(122, Calendar.JUNE, 20),new Date(122, Calendar.AUGUST, 15)};

        flightDao.save(new Flight("American Delta 2", dates3, 240.00));

    }
}
