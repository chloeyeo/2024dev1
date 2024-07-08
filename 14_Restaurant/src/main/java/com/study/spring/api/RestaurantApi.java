package com.study.spring.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.spring.api.request.CreateAndEditRestaurantRequest;
import com.study.spring.model.RestaurantEntity;
import com.study.spring.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
	private final RestaurantService restaurantService;
	@GetMapping("/restaurants")
	public String getRestaurants() {
		return "getRestaurants";
	}
	@GetMapping("/restaurant/{restaurantId}")
	public String getRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		return "getRestaurant Id : " + restaurantId;
	}
	@PostMapping("/restaurant")
	public RestaurantEntity createRestaurant(@RequestBody CreateAndEditRestaurantRequest request) {
//		return "createRestaurant name : "+request.getName()+", address: "
//				+request.getAddress()+", menus[0].name: "+request.getMenus().get(0).getName()
//				+", menus[1].name: "+request.getMenus().get(1).getName();
		return restaurantService.createRestaurant(request);
	}
	@PutMapping("/restaurant/{restaurantId}")
	public String editRestaurant(@PathVariable("restaurantId") Long restaurantId, @RequestBody CreateAndEditRestaurantRequest request) {
		return "editRestaurant id: "+restaurantId+", name : "+request.getName()+", address: "
				+request.getAddress()+", menus[0].name: "+request.getMenus().get(0).getName()
				+", menus[1].name: "+request.getMenus().get(1).getName();
	}
	@DeleteMapping("/restaurant/{restaurantId}")
	public String deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		return "deleteRestaurant Id : " + restaurantId;
	}
}
