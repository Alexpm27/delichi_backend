package com.ampei.delichi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.ampei.delichi.persistance.models.Restaurant;
import com.ampei.delichi.persistance.repositories.IRestaurantRepository;
import com.ampei.delichi.services.impls.RestaurantServiceImpl;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.RestaurantResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class RestaurantServiceImplTest {

    private IRestaurantRepository repository;
    private RestaurantServiceImpl restaurantService;

    @Before
    public void setUp() {
        repository = mock(IRestaurantRepository.class);
        restaurantService = new RestaurantServiceImpl(repository);
    }

    @Test
    public void testGetRestaurant() {
        Long restaurantId = 1L;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Main St");
        restaurant.setPhoneNumber(1234567890L);
        when(repository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        BaseResponse response = restaurantService.get(restaurantId);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("Restaurant Obtained", response.getDetail());

        RestaurantResponse restaurantResponse = (RestaurantResponse) response.getData();
        assertEquals(restaurantId, restaurantResponse.getId());
        assertEquals("Test Restaurant", restaurantResponse.getName());
        assertEquals("123 Main St", restaurantResponse.getAddress());
        assertEquals("123-456-7890", restaurantResponse.getPhoneNumber());
    }
}
