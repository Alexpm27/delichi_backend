package com.ampei.delichi.services.impls;

import com.ampei.delichi.persistance.models.Review;
import com.ampei.delichi.persistance.repositories.IReviewRepository;
import com.ampei.delichi.services.interfaces.IRestaurantService;
import com.ampei.delichi.services.interfaces.IReviewService;
import com.ampei.delichi.services.interfaces.IUserService;
import com.ampei.delichi.web.dtos.requests.CreateReviewRequest;
import com.ampei.delichi.web.dtos.requests.UpdateReviewRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.ReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {

    private final IReviewRepository repository;

    private final IRestaurantService iRestaurantService;

    private final IUserService iUserService;

    public ReviewServiceImpl(IReviewRepository repository, IRestaurantService iRestaurantService, IUserService iUserService) {
        this.repository = repository;
        this.iRestaurantService = iRestaurantService;
        this.iUserService = iUserService;
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(findAndEnsureExist(id)))
                .detail("Review Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse listAlLByRestaurantId(Long id) {
        return BaseResponse.builder()
                .data(listReviews(id))
                .detail("List Reviews Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse create(CreateReviewRequest request, Long userId, Long restaurantId) {
        return BaseResponse.builder()
                .data(from(from(request, userId, restaurantId)))
                .detail("Review Created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse update(UpdateReviewRequest request, Long id) {
        return BaseResponse.builder()
                .data(from(from(request, id)))
                .detail("Review Updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .detail("Restaurant Deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private Review findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public ReviewResponse from(Review review){
        return ReviewResponse.builder()
                .id(review.getId())
                .date(review.getDate())
                .content(review.getContent())
                .userId(review.getUser().getId())
                .userName(review.getUser().getName())
                .restaurantId(review.getRestaurant().getId())
                .restaurantName(review.getRestaurant().getName())
                .build();
    }

    private List<ReviewResponse> listReviews(Long id){
        List<Review> reservations = repository.findAllByRestaurant_Id(id).orElseThrow(RuntimeException::new);
        return reservations.stream()
                .map(this::from)
                .toList();
    }

    private Review from(CreateReviewRequest request, Long idUser, Long restaurantId){
        Review review = new Review();
        review.setDate(LocalDateTime.now());
        review.setContent(request.getContent());
        review.setScore(request.getScore());
        review.setUser(iUserService.findAndEnsureExist(idUser));
        review.setRestaurant(iRestaurantService.findAndEnsureExist(restaurantId));
        return repository.save(review);
    }

    private Review from(UpdateReviewRequest request, Long id){
        Review review = findAndEnsureExist(id);
        if (!review.getContent().equals(request.getContent())) {
            review.setContent(request.getContent());
        }
        if (!review.getScore().equals(request.getScore())) {
            review.setScore(request.getScore());
        }
        if (!review.getContent().equals(request.getContent()) || !review.getScore().equals(request.getScore())) {
            review.setDate(LocalDateTime.now());
            return repository.save(review);
        }else {
            return review;
        }
    }

}