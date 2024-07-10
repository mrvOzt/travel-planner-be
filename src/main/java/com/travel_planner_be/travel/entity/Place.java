package com.travel_planner_be.travel.entity;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Place {

    @Id
    private String id;

    @NotBlank(message = "Please enter a valid place name")
    @Size(max = 100)
    private String place_name;

    @Min(value = -90, message = "Latitude must be big than -90")
    @Max(value = 90 , message = "Latitude must be small than 90")
    private double latitude;

    @Min(value = -180, message = "Latitude must be big than -180")
    @Max(value = 180 , message = "Latitude must be small than 180")
    private double longitude;

    @NotBlank(message = "Please enter image url.")
    private String imageUrl;

    @NotBlank(message = "Please enter country")
    private String country;

    @NotBlank(message = "Please enter country")
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String duration;

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Please enter type information")
    private String type;

    private List<String> tag;

    private int popularityRate;

    @Nullable
    private boolean IsVegan;



}
