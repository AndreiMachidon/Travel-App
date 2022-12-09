package com.springflutter.demo.service;

import com.springflutter.demo.dao.HotelDao;
import com.springflutter.demo.dao.ImageModelDao;
import com.springflutter.demo.entity.Hotel;
import com.springflutter.demo.entity.HotelSearchResponse;
import com.springflutter.demo.entity.ImageModel;
import com.springflutter.demo.util.ImageModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    ImageModelUtil imageModelUtil;

    @Autowired
    ImageModelDao imageModelDao;

    public List<Hotel> getAllHotels(){
        return hotelDao.findAll();
    }

    /**
     * This method find a hotel based on user's criteria
     * @return the list of available hotels
     */
    public List<HotelSearchResponse> searchHotels(String location, Date startDate, Date endDate, Integer numberOfPersons){
        List<Hotel> allExistingHotels = this.getAllHotels();
        List<Hotel> availableHotels = new ArrayList<>();

        //first, we search for the hotels based on their availability
        for(Hotel hotel: allExistingHotels){
            if (hotel.getLocation().toLowerCase().equals(location)) {
                if(hotel.getAvailableStartDate().compareTo(startDate) < 0){
                    if(hotel.getAvailableEndDate().compareTo(endDate) > 0){
                        if(hotel.isBooked() == false){
                            availableHotels.add(hotel);
                        }
                    }
                }
            }

        }

        //after we found the available hotels, we calculate their price and other details
        List<HotelSearchResponse> hotelResponses= new ArrayList<>();
        for(Hotel hotel : availableHotels) {
            HotelSearchResponse reponse = new HotelSearchResponse();
            reponse.setHotelName(hotel.getName());
            reponse.setDestination(hotel.getLocation());
            reponse.setDescription(hotel.getDescription());
            reponse.setTotalPrice(HotelSearchResponse.calculateTotalPrice(hotel.getPricePerNight(), numberOfPersons, startDate, endDate));
            reponse.setPictures(hotel.getHotelImages());

            hotelResponses.add(reponse);

        }
        return  hotelResponses;

    }

    /**
     * This method lets the user book a hotel
     */
    public String bookHotel(Integer id){
        Hotel bookedHotel = hotelDao.findById(id).get();

        bookedHotel.setBooked(true);

        return "Hotel "+bookedHotel.getName() + " was booked";
    }

    public Hotel addHotel(Hotel hotel){
        return hotelDao.save(hotel);
    }

    @PostConstruct
    /**
     * This method populate the database with hotels before the app starts
     */
    private void initHotels() throws IOException {

       ImageModel image1 =  new ImageModel("poza1.jpg", "jpg", imageModelUtil.convertImageToBytes("poza1.jpg", "jpg"));
       ImageModel image2 = new ImageModel("poza2.jpg", "jpg", imageModelUtil.convertImageToBytes("poza2.jpg", "jpg"));
       HashSet<ImageModel> images1= new HashSet<>(Set.of(image1, image2));
        hotelDao.save(
                new Hotel(
                        "The Hive Hotel", "Featuring a shared rooftop terrace, The Hive Hotel is 300 m from Santa Maria Maggiore Basilica.",
                        "italy", new Date(122, Calendar.JUNE, 20),new Date(122, Calendar.AUGUST, 15),
                           images1, 200.00)
        );

        ImageModel image3 =  new ImageModel("poza3.jpg", "jpg", imageModelUtil.convertImageToBytes("poza3.jpg", "jpg"));
        ImageModel image4 = new ImageModel("poza4.jpg", "jpg", imageModelUtil.convertImageToBytes("poza4.jpg", "jpg"));

        HashSet<ImageModel> images2= new HashSet<>(Set.of(image3, image4));

        hotelDao.save(
                new Hotel(
                        "Mia roma centro", "In the Pantheon district of Rome, close to Palazzo Venezia, Mia roma centro has a shared lounge, free WiFi and a washing machine. The property features city views and is 300 m from Largo di Torre Argentina and 700 m from Piazza Venezia.",
                        "italy", new Date(122, Calendar.SEPTEMBER, 21),new Date(122, Calendar.JANUARY, 13),
                        images2, 150.00)
        );

        ImageModel image5 =  new ImageModel("poza5.jpg", "jpg", imageModelUtil.convertImageToBytes("poza5.jpg", "jpg"));
        ImageModel image6 = new ImageModel("poza6.jpg", "jpg", imageModelUtil.convertImageToBytes("poza6.jpg", "jpg"));

        HashSet<ImageModel> images3= new HashSet<>(Set.of(image5, image6));

        hotelDao.save(
                new Hotel(
                        "The Hive Hotel", "Featuring a shared rooftop terrace, The Hive Hotel is 300 m from Santa Maria Maggiore Basilica.",
                        "spain", new Date(122, Calendar.JUNE, 20),new Date(122, Calendar.AUGUST, 15),
                        images3, 170.00)
        );

        ImageModel image7 =  new ImageModel("poza7.jpg", "jpg", imageModelUtil.convertImageToBytes("poza7.jpg", "jpg"));
        ImageModel image8 = new ImageModel("poza8.jpg", "jpg", imageModelUtil.convertImageToBytes("poza8.jpg", "jpg"));

        HashSet<ImageModel> images4 = new HashSet<>(Set.of(image7, image8));

        hotelDao.save(
                new Hotel(
                        " Villa Es Pas", "Featuring air-conditioned accommodation with a private pool, sea view and a patio, Villa Es Pas is located in S'Estanyol de Migjorn. This holiday home provides a garden, barbecue facilities as well as free WiFi.",
                        "spain", new Date(122, Calendar.SEPTEMBER, 21),new Date(122, Calendar.JANUARY, 5),
                        images4, 210.00)
        );





    }




}
