package com.ampei.delichi.services.interfaces;

import com.ampei.delichi.persistance.models.Review;
import com.ampei.delichi.web.dtos.requests.CreateReviewRequest;
import com.ampei.delichi.web.dtos.requests.UpdateReviewRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.ReviewResponse;

public interface IReviewService {

    BaseResponse get(Long id);

    BaseResponse listAlLByRestaurantId(Long id);

    BaseResponse create(CreateReviewRequest request, Long userId, Long restaurantId);

    BaseResponse update(UpdateReviewRequest request, Long id);

    BaseResponse delete(Long id);

    ReviewResponse from(Review review);

}