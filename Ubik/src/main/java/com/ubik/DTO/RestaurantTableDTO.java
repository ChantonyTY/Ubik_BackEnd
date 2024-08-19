package com.ubik.DTO;

import lombok.Data;

@Data
public class RestaurantTableDTO {
	private Long id;
    private String name;
    private Integer seats;
    private Integer duration;
    private Boolean occupied; // Nouveau champ
}