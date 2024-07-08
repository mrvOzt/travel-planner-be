package com.travel_planner_be.travel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class Category {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
}
