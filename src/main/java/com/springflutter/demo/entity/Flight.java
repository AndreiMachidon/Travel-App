package com.springflutter.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String planeModel;
    Date[] flightDates;
    Double ticketPrice;

    public Flight(String planeModel, Date[] flightDates, Double ticketPrice) {
        this.planeModel = planeModel;
        this.flightDates = flightDates;
        this.ticketPrice = ticketPrice;
    }
}
