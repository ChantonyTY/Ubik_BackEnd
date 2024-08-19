package com.ubik.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubik.DTO.RestaurantTableDTO;
import com.ubik.Model.RestaurantTable;
import com.ubik.Service.RestaurantTableService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/api/tables")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    // Récupère toutes les tables
    @GetMapping
    public List<RestaurantTableDTO> getAllTables() {
        return restaurantTableService.getAllTables().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Récupère une table par son ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableDTO> getTableById(@PathVariable Long id) {
        RestaurantTable table = restaurantTableService.getTableById(id);
        if (table != null) {
            return ResponseEntity.ok(convertToDTO(table));
        }
        return ResponseEntity.notFound().build();
    }

    // Crée une nouvelle table
    @PostMapping
    public ResponseEntity<RestaurantTableDTO> createTable(@RequestBody RestaurantTableDTO tableDTO) {
        RestaurantTable table = convertToEntity(tableDTO);
        RestaurantTable newTable = restaurantTableService.saveTable(table);
        return ResponseEntity.ok(convertToDTO(newTable));
    }

    // Met à jour une table existante
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableDTO> updateTable(@PathVariable Long id, @RequestBody RestaurantTableDTO tableUpdateDTO) {
        RestaurantTable existingTable = restaurantTableService.getTableById(id);
        if (existingTable != null) {
            if (tableUpdateDTO.getName() != null) {
                existingTable.setName(tableUpdateDTO.getName());
            }
            if (tableUpdateDTO.getDuration() != null) {
                existingTable.setDuration(tableUpdateDTO.getDuration());
            }
            if (tableUpdateDTO.getOccupied() != null) {
                existingTable.setOccupied(tableUpdateDTO.getOccupied());
            }
            if (tableUpdateDTO.getSeats() != existingTable.getSeats()) {
                existingTable.setSeats(tableUpdateDTO.getSeats());
            }
            RestaurantTable updatedTable = restaurantTableService.saveTable(existingTable);
            return ResponseEntity.ok(convertToDTO(updatedTable));
        }
        return ResponseEntity.notFound().build();
    }

    // Supprime une table par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        RestaurantTable existingTable = restaurantTableService.getTableById(id);
        if (existingTable != null) {
            restaurantTableService.deleteTable(id);
            return ResponseEntity.noContent().build(); // Réponse avec statut 204 No Content
        }
        return ResponseEntity.notFound().build();
    }

    // Convertit une entité RestaurantTable en DTO
    private RestaurantTableDTO convertToDTO(RestaurantTable table) {
        RestaurantTableDTO dto = new RestaurantTableDTO();
        dto.setId(table.getId());
        dto.setName(table.getName());
        dto.setSeats(table.getSeats());
        dto.setDuration(table.getDuration());
        dto.setOccupied(table.getOccupied()); // Nouveau champ
        return dto;
    }

    // Convertit un DTO en entité RestaurantTable
    private RestaurantTable convertToEntity(RestaurantTableDTO dto) {
        RestaurantTable table = new RestaurantTable();
        table.setName(dto.getName());
        table.setSeats(dto.getSeats());
        table.setDuration(dto.getDuration());
        table.setOccupied(dto.getOccupied()); // Nouveau champ
        return table;
    }
}
