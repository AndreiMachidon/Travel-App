package com.springflutter.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name= "description", length = 999999999)
    private String description;

    @Column(name="location")
    private String location;

    @Column(name="available_start_date")
    private Date availableStartDate;

    @Column(name="available_end_date")
    private Date availableEndDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="hotel_images",  //creates a new table named product_images
            joinColumns = {
                    @JoinColumn(name = "hotel_id") //creates a column on the new table named product_id which will contain the id of the product
            },
            inverseJoinColumns = {
                    @JoinColumn(name="image_id") //creates a column on the new table names image_id which will contain the id of the image
            }
    )
    private Set<ImageModel> hotelImages;

    @Column(name="price_per_night")
    private Double pricePerNight;

    @Column(name="is_booked")
    private boolean isBooked = false;


    public Hotel(String name, String description, String location, Date availableStartDate, Date availableEndDate, Set<ImageModel> productImages, Double pricePerNight) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.hotelImages = productImages;
        this.pricePerNight = pricePerNight;
    }

}
