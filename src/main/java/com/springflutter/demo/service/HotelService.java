package com.springflutter.demo.service;

import com.springflutter.demo.dao.HotelDao;
import com.springflutter.demo.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelDao hotelDao;

    public List<Hotel> getAllHotels(){
        return hotelDao.findAll();
    }

    public List<Hotel> searchHotels(String location, Date startDate, Date endDate){
        List<Hotel> allExistingHotels = this.getAllHotels();
        List<Hotel> availableHotels = new ArrayList<>();

        for(Hotel hotel: allExistingHotels){
            if (hotel.getLocation().equals(location)) {
                if(hotel.getAvailableStartDate().compareTo(startDate) < 0){
                    if(hotel.getAvailableEndDate().compareTo(endDate) > 0){
                        availableHotels.add(hotel);
                    }
                }
            }

        }
        return availableHotels;
    }

}
