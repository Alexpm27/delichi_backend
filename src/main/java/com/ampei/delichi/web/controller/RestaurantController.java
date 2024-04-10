package com.ampei.delichi.web.controller;

import com.ampei.delichi.services.interfaces.IRestaurantService;
import com.ampei.delichi.web.dtos.requests.CreateRestaurantRequest;
import com.ampei.delichi.web.dtos.requests.UpdateRestaurantRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final IRestaurantService service;

    public RestaurantController(IRestaurantService service) {
        this.service = service;
    }

    @GetMapping("get/v1/{id}")
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable Long id){
        return service.get(id).apply();
    }

    @GetMapping("list/restaurants/v1")
    public ResponseEntity<BaseResponse> list(){
        return service.list().apply();
    }

    @GetMapping("list/restaurants/name/v1/{name}")
    public ResponseEntity<BaseResponse> listRestaurantsByName(@Valid @PathVariable String name){
        return service.listRestaurantsByName(name).apply();
    }

    @PostMapping("create/v1")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateRestaurantRequest request){
        return service.create(request).apply();
    }

    @PutMapping("update/v1/{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateRestaurantRequest request,
                                               @Valid @PathVariable Long id){
        return service.update(request, id).apply();
    }

    @DeleteMapping("delete/v1/{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        return service.delete(id).apply();
    }

}