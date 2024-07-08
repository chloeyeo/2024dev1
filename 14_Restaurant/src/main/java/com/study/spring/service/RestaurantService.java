package com.study.spring.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.spring.api.request.CreateAndEditRestaurantRequest;
import com.study.spring.model.MenuEntity;
import com.study.spring.model.RestaurantEntity;
import com.study.spring.repository.MenuRepository;
import com.study.spring.repository.RestaurantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	private final MenuRepository menuRepository;

	@Transactional
	public RestaurantEntity createRestaurant(CreateAndEditRestaurantRequest request) {
		// We're using CreateAndEditRestaurantRequest as DTO. We can use DTO anywhere.
//		RestaurantEntity restaurant = new RestaurantEntity(request.getName(),...); <- don't have to use this when using builder!
		// if we don't know how many params & which each param is/order of param then we
		// can use builder instead of 'new'
		// using builder we don't have to add parameters that we don't need when
		// instantiating the entity
		RestaurantEntity restaurant = RestaurantEntity.builder().name(request.getName()).address(request.getAddress())
				.createdAt(ZonedDateTime.now()).updatedAt(ZonedDateTime.now()).build(); // converts request data to
																						// entity
		restaurantRepository.save(restaurant);
		request.getMenus().forEach(menu -> {
//			MenuEntity menuEntity = new MenuEntity();
			MenuEntity menuEntity = MenuEntity.builder().restaurantId(restaurant.getId()).name(menu.getName())
					.price(menu.getPrice()).createdAt(ZonedDateTime.now()).updatedAt(ZonedDateTime.now()).build();
			menuRepository.save(menuEntity);
		});

		return restaurant;
	}

	@Transactional
	public void editRestaurant(Long restaurantId, CreateAndEditRestaurantRequest request) {
		RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RuntimeException("restaurant id not found"));
		restaurant.changeNameAndAddress(request.getName(), request.getAddress());
		restaurantRepository.save(restaurant);

		// delete previous menu
		List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
		menuRepository.deleteAll(menus);

		// create new menu
		request.getMenus().forEach(menu -> {
			MenuEntity menuEntity = MenuEntity.builder().restaurantId(restaurantId).name(menu.getName())
					.price(menu.getPrice()).createdAt(ZonedDateTime.now()).updatedAt(ZonedDateTime.now()).build();
			menuRepository.save(menuEntity);
		});
	}

	@Transactional
	public void deleteRestaurant(Long restaurantId) {
		RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RuntimeException("restaurant id not found"));
		restaurantRepository.delete(restaurant);
		List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
		menuRepository.deleteAll(menus);
	}
}
