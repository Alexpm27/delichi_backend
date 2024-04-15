package com.ampei.delichi.services.impls;

import com.ampei.delichi.persistance.models.Restaurant;
import com.ampei.delichi.persistance.repositories.IRestaurantRepository;
import com.ampei.delichi.services.interfaces.IRestaurantService;
import com.ampei.delichi.services.interfaces.IReviewService;
import com.ampei.delichi.web.dtos.requests.CreateRestaurantRequest;
import com.ampei.delichi.web.dtos.requests.UpdateRestaurantRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.responses.RestaurantResponse;
import com.ampei.delichi.web.dtos.responses.ReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantRepository repository;

    private final IReviewService iReviewService;

    public RestaurantServiceImpl(IRestaurantRepository repository, IReviewService iReviewService) {
        this.repository = repository;
        this.iReviewService = iReviewService;
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(findAndEnsureExist(id)))
                .detail("Restaurant Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse list() {
        return BaseResponse.builder()
                .data(listRestaurants())
                .detail("List Restaurants Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse listRestaurantsByName(String name) {
        return BaseResponse.builder()
                .data(listByName(name))
                .detail("List Restaurants By Name Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse create(CreateRestaurantRequest request) {
        return BaseResponse.builder()
                .data(from(from(request)))
                .detail("Restaurant Created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse update(UpdateRestaurantRequest request, Long id) {
        return BaseResponse.builder()
                .data(from(from(request, id)))
                .detail("Restaurant Updated")
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

    @Override
    public Restaurant findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    private RestaurantResponse from(Restaurant restaurant){
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .kitchen(restaurant.getKitchen())
                .schedule(restaurant.getSchedule())
                .reviewResponseList(reviewResponseList(restaurant))
                .build();
    }

    private List<ReviewResponse> reviewResponseList(Restaurant restaurant){
        return restaurant.getReviews().stream().map(review -> {
            return ReviewResponse.builder()
                .id(review.getId())
                .date(review.getDate())
                .content(review.getContent())
                .score(review.getScore())
                .userId(review.getUser().getId())
                .userName(review.getUser().getName())
                .restaurantId(review.getRestaurant().getId())
                .restaurantName(review.getRestaurant().getName())
                .build();
        }).toList();
    }

    private List<RestaurantResponse> listRestaurants(){
        List<Restaurant> restaurantList = repository.findAllRestaurants().orElseThrow(RuntimeException::new);
        return restaurantList.stream()
                .map(this::from)
                .toList();
    }

    private List<RestaurantResponse> listByName(String name){
        List<Restaurant> restaurantList = repository.findAllByName(name).orElseThrow(RuntimeException::new);
        return restaurantList.stream()
                .map(this::from)
                .toList();
    }

    private Restaurant from(CreateRestaurantRequest request){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setSchedule(request.getSchedule());
        return repository.save(restaurant);
    }

    private Restaurant from(UpdateRestaurantRequest request, Long id) {
        Restaurant restaurant = findAndEnsureExist(id);
        if (!restaurant.getName().equals(request.getName())) {
            restaurant.setName(request.getName());
        }
        if (!restaurant.getAddress().equals(request.getAddress())) {
            restaurant.setAddress(request.getAddress());
        }
        if (!restaurant.getKitchen().equals(request.getKitchen())) {
            restaurant.setKitchen(request.getKitchen());
        }
        if (!restaurant.getPhoneNumber().equals(request.getPhoneNumber())) {
            restaurant.setPhoneNumber(request.getPhoneNumber());
        }
        if (!restaurant.getSchedule().equals(request.getSchedule())) {
            restaurant.setSchedule(request.getSchedule());
        }
        if (!restaurant.getName().equals(request.getName()) || !restaurant.getAddress().equals(request.getAddress()) || !restaurant.getKitchen().equals(request.getKitchen()) || !restaurant.getPhoneNumber().equals(request.getPhoneNumber()) || !restaurant.getSchedule().equals(request.getSchedule())){
            return repository.save(restaurant);
        }else {
            return restaurant;
        }
    }

}