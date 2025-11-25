package com.akash.food_inventory_service.dto;

import com.akash.food_inventory_service.entity.FoodInventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: akash
 * Date: 18/11/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodInventoryPage {

    private List<FoodInventoryDTO> foodInventoryList;
    private RestaurantDTO restaurantDTO;
}
