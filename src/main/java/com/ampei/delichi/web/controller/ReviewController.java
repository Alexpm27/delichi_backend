package com.ampei.delichi.web.controller;

import com.ampei.delichi.services.interfaces.IReviewService;
import com.ampei.delichi.web.dtos.requests.CreateReviewRequest;
import com.ampei.delichi.web.dtos.requests.UpdateReviewRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController {

    private final IReviewService service;

    public ReviewController(IReviewService service) {
        this.service = service;
    }

    @GetMapping("v1/get/{id}")
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable Long id){
        return service.get(id).apply();
    }

    @GetMapping("v1/list/restaurant/{id}")
    public ResponseEntity<BaseResponse> listByRestaurantId(@Valid @PathVariable Long id){
        return service.listAlLByRestaurantId(id).apply();
    }

    @PostMapping("v1/create/user/{idUser}/restaurant/{idRestaurant}")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateReviewRequest request,
                                               @Valid @PathVariable Long idUser,
                                               @Valid @PathVariable Long idRestaurant){
        return service.create(request, idUser, idRestaurant).apply();
    }

    @PutMapping("v1/update/{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateReviewRequest request,
                                               @Valid @PathVariable Long id){
        return service.update(request, id).apply();
    }

    @DeleteMapping("v1/delete/{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        return service.delete(id).apply();
    }

}