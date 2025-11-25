package com.akash.food_inventory_service.service;

import com.akash.food_inventory_service.dto.FoodInventoryDTO;
import com.akash.food_inventory_service.dto.FoodInventoryPage;
import com.akash.food_inventory_service.dto.RestaurantDTO;
import com.akash.food_inventory_service.entity.FoodInventory;
import com.akash.food_inventory_service.mapper.FoodInventoryMapper;
import com.akash.food_inventory_service.repository.FoodInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: akash
 * Date: 18/11/25
 */

@RequiredArgsConstructor
@Service
public class FoodInventoryService {

    private final FoodInventoryRepository foodInventoryRepository;
    private final RestTemplate restTemplate;

    @Value("${services.restaurant-onboarding.url}")
    private String restaurantOnboardingUrl;


    public FoodInventoryDTO addFoodInventory(FoodInventoryDTO foodItemDTO) {
        FoodInventory foodItemSavedInDB = foodInventoryRepository.save(FoodInventoryMapper.INSTANCE.mapFoodInventoryDTOToFoodInventory(foodItemDTO));
        return FoodInventoryMapper.INSTANCE.mapFoodInventoryToFoodInventoryDto(foodItemSavedInDB);
    }

    private List<FoodInventory> findFoodInventoryList(Long restaurantId) {
        return foodInventoryRepository.findByRestaurantId(restaurantId);
    }

    public FoodInventoryPage findFoodInventoryPageDetails(Long restaurantId) {
        List<FoodInventory> foodInventoryList =  findFoodInventoryList(restaurantId);
        RestaurantDTO restaurantDTO = findRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodInventoryPage(foodInventoryList, restaurantDTO);
    }

    private FoodInventoryPage createFoodInventoryPage(List<FoodInventory> foodItemList, RestaurantDTO restaurantDTO) {
        FoodInventoryPage foodInventoryPage = new FoodInventoryPage();
        foodInventoryPage.setFoodInventoryList(foodItemList.stream().map(foodInventory -> FoodInventoryMapper.INSTANCE.mapFoodInventoryToFoodInventoryDto(foodInventory)).collect(Collectors.toList()));
        foodInventoryPage.setRestaurantDTO(restaurantDTO);
        return foodInventoryPage;
    }

    private RestaurantDTO findRestaurantDetailsFromRestaurantMS(Long restaurantId) {
        return restTemplate.getForObject(restaurantOnboardingUrl+"/restaurant/getById/"+restaurantId, RestaurantDTO.class);
    }
}
