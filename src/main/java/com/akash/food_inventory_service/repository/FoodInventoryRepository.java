package com.akash.food_inventory_service.repository;

import com.akash.food_inventory_service.entity.FoodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: akash
 * Date: 18/11/25
 */
@Repository
public interface FoodInventoryRepository extends JpaRepository<FoodInventory, Long> {
    List<FoodInventory> findByRestaurantId(Long restaurantId);
}
