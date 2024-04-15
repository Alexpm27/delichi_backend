package com.ampei.delichi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.ampei.delichi.persistance.models.Review;
import com.ampei.delichi.persistance.repositories.IReviewRepository;
import com.ampei.delichi.services.impls.ReviewServiceImpl;
import com.ampei.delichi.services.interfaces.IRestaurantService;
import com.ampei.delichi.services.interfaces.IUserService;
import com.ampei.delichi.web.dtos.requests.CreateReviewRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.ReviewResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewServiceImplTest {

    private IReviewRepository repository;
    private IRestaurantService restaurantService;
    private IUserService userService;
    private ReviewServiceImpl reviewService;

    @Before
    public void setUp() {
        repository = mock(IReviewRepository.class);
        restaurantService = mock(IRestaurantService.class);
        userService = mock(IUserService.class);
        reviewService = new ReviewServiceImpl(repository, restaurantService, userService);
    }

    @Test
    public void testGetReview() {
        Long reviewId = 1L;
        Review review = new Review();
        review.setId(reviewId);
        review.setContent("Test review");
        review.setScore(5);
        review.setDate(LocalDateTime.now());

        when(repository.findById(reviewId)).thenReturn(Optional.of(review));

        BaseResponse response = reviewService.get(reviewId);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("Review Obtained", response.getDetail());

        ReviewResponse reviewResponse = (ReviewResponse) response.getData();
        assertEquals(reviewId, reviewResponse.getId());
        assertEquals("Test review", reviewResponse.getContent());
        assertEquals(Optional.of(5), reviewResponse.getScore());
    }

    @Test
    public void testListAllReviewsByRestaurantId() {
        Long restaurantId = 1L;
        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent("Review 1");
        review1.setScore(4);
        review1.setDate(LocalDateTime.now());

        Review review2 = new Review();
        review2.setId(2L);
        review2.setContent("Review 2");
        review2.setScore(3);
        review2.setDate(LocalDateTime.now());

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review1);
        reviewList.add(review2);

        when(repository.findAllByRestaurant_Id(restaurantId)).thenReturn(Optional.of(reviewList));

        BaseResponse response = reviewService.listAlLByRestaurantId(restaurantId);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("List Reviews Obtained", response.getDetail());

        List<ReviewResponse> reviewResponses = (List<ReviewResponse>) response.getData();
        assertEquals(2, reviewResponses.size());
        assertEquals("Review 1", reviewResponses.get(0).getContent());
        assertEquals("Review 2", reviewResponses.get(1).getContent());
    }
}
