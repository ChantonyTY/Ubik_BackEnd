package com.ubik.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubik.Model.RestaurantTable;
import com.ubik.Repository.RestaurantTableRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTableService {

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    // Récupère toutes les tables
    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepository.findAll();
    }

    // Récupère une table par son ID
    public RestaurantTable getTableById(Long id) {
        return restaurantTableRepository.findById(id).orElse(null);
    }

    // Crée ou met à jour une table
    @Transactional
    public RestaurantTable saveTable(RestaurantTable table) {
        return restaurantTableRepository.save(table);
    }

    // Supprime une table par son ID
    @Transactional
    public void deleteTable(Long id) {
        restaurantTableRepository.deleteById(id);
    }
}