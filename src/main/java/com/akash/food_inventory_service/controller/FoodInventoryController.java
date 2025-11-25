package com.akash.food_inventory_service.controller;

import com.akash.food_inventory_service.dto.FoodInventoryDTO;
import com.akash.food_inventory_service.dto.FoodInventoryPage;
import com.akash.food_inventory_service.service.FoodInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: akash
 * Date: 18/11/25
 */

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/foodInventory")
public class FoodInventoryController {

    private final FoodInventoryService foodCatalogueService;

    @PostMapping("/addFood")
    public ResponseEntity<FoodInventoryDTO> addFood(@RequestBody FoodInventoryDTO foodInventoryDTO){
        FoodInventoryDTO savedFood = foodCatalogueService.addFoodInventory(foodInventoryDTO);
        return new ResponseEntity<>(savedFood, HttpStatus.CREATED);
    }

    @GetMapping("/findRestaurantAndFoodInventoryById/{restaurantId}")
    public ResponseEntity<FoodInventoryPage> fetchRestauDetailsWithFoodMenu(@PathVariable Long restaurantId){
        FoodInventoryPage foodInventoryPage = foodCatalogueService.findFoodInventoryPageDetails(restaurantId);
        return new ResponseEntity<>(foodInventoryPage, HttpStatus.OK);


    }
}
