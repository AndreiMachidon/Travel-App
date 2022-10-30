package com.springflutter.demo.controller;

import com.springflutter.demo.entity.Hotel;
import com.springflutter.demo.entity.HotelForm;
import com.springflutter.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "search")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping(path = "/hotels")
    private List<Hotel> searchHotels(@RequestBody HotelForm hotelForm){
        return hotelService.searchHotels(hotelForm.getLocation(), hotelForm.getStartDate(), hotelForm.getEndDate());
    }
}
