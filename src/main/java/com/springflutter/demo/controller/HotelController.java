package com.springflutter.demo.controller;

import com.springflutter.demo.entity.Hotel;
import com.springflutter.demo.dto.HotelForm;
import com.springflutter.demo.entity.HotelSearchResponse;
import com.springflutter.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping(path = "/search")
    private List<HotelSearchResponse> searchHotels(@RequestBody HotelForm hotelForm){
        return hotelService.searchHotels(hotelForm.getLocation(), hotelForm.getStartDate(), hotelForm.getEndDate(), hotelForm.getNumberOfPersons());
    }

    @PostMapping(path = "/add")
    private Hotel addHotel(@RequestBody Hotel hotel){
        return  hotelService.addHotel(hotel);
    }

    @PostMapping(path="/book/{id}")
    private String bookHotel(@PathVariable int id){
        return hotelService.bookHotel(id);
    }


}
