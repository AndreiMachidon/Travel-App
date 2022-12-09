package com.springflutter.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This class is the response sent to the client when he searches for hotels
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelSearchResponse {

    String hotelName;
    String destination;
    String description;
    Double totalPrice;
    Set<ImageModel> pictures;

    /**
     * This method calculates the total price for the hotel baes on the number of persons, the hotel price and the date
     */
    public static Double calculateTotalPrice(Double price,Integer numberOfPersons, Date startDate, Date endDate){


        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return price* numberOfPersons* daysBetween;
    }
}
