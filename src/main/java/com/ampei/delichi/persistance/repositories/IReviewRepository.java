package com.ampei.delichi.persistance.repositories;

import com.ampei.delichi.persistance.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {

    Optional<List<Review>> findAllByRestaurant_Id(Long restaurantId);

}