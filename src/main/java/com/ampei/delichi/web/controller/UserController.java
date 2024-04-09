package com.ampei.delichi.web.controller;

import com.ampei.delichi.services.interfaces.IUserService;
import com.ampei.delichi.web.dtos.requests.GetUserRequest;
import com.ampei.delichi.web.dtos.requests.LoginUserRequest;
import com.ampei.delichi.web.dtos.requests.UpdateUserRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;
import com.ampei.delichi.web.dtos.requests.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping("get/v1")
    public ResponseEntity<BaseResponse> get(@Valid @RequestBody GetUserRequest request){
        return service.get(request).apply();
    }

    @PostMapping("login/v1")
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody LoginUserRequest request){
        return service.login(request).apply();
    }

    @PostMapping("create/v1")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateUserRequest request){
        return service.create(request).apply();
    }

    @PutMapping("update/v1/{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateUserRequest request, @Valid @PathVariable Long id){
        return service.update(request, id).apply();
    }

    @DeleteMapping("delete/v1/{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        return service.delete(id).apply();
    }

}