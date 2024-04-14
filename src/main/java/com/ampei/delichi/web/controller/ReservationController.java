package com.ampei.delichi.web.controller;

import com.ampei.delichi.services.interfaces.IReservationService;
import com.ampei.delichi.web.dtos.requests.*;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final IReservationService service;

    public ReservationController(IReservationService service) {
        this.service = service;
    }

    @GetMapping("v1/get/{id}")
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable Long id){
        return service.get(id).apply();
    }

    @GetMapping("v1/list/user/{id}")
    public ResponseEntity<BaseResponse> list(@Valid @PathVariable Long id){
        return service.list(id).apply();
    }

//    @GetMapping("v1/list/user/{id}")
//    public ResponseEntity<BaseResponse> listByRestaurant(@Valid @PathVariable Long id){
//        return service.list(id).apply();
//    }

    @PostMapping("v1/create/user/{idUser}/restaurant/{idRestaurant}")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateReservationRequest request,
                                               @Valid @PathVariable Long idUser,
                                               @Valid @PathVariable Long idRestaurant){
        return service.create(request, idUser, idRestaurant).apply();
    }

    @PutMapping("v1/update/{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateReservationRequest request,
                                               @Valid @PathVariable Long id){
        return service.update(request, id).apply();
    }

    @DeleteMapping("v1/delete/{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        return service.delete(id).apply();
    }

}