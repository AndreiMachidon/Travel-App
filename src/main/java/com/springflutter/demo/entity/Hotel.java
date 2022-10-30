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

    @Column(name= "description")
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
    private Set<ImageModel> productImages;
}
