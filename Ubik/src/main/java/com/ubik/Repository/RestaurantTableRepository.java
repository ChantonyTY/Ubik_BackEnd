package com.ubik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubik.Model.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}