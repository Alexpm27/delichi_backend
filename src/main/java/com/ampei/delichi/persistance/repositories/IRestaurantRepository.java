package com.ampei.delichi.persistance.repositories;

import com.ampei.delichi.persistance.models.Restaurant;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r")
    Optional<List<Restaurant>> findAllRestaurants();

    Optional<List<Restaurant>> findAllByName(String name);

}