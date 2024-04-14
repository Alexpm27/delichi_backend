package com.ampei.delichi.services.interfaces;

import com.ampei.delichi.persistance.models.User;
import com.ampei.delichi.web.dtos.requests.CreateUserRequest;
import com.ampei.delichi.web.dtos.requests.GetUserRequest;
import com.ampei.delichi.web.dtos.requests.LoginUserRequest;
import com.ampei.delichi.web.dtos.requests.UpdateUserRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;

public interface IUserService {

    BaseResponse get(GetUserRequest request);

    BaseResponse login(LoginUserRequest request);

    BaseResponse create(CreateUserRequest request);

    BaseResponse update(UpdateUserRequest request, Long id);

    BaseResponse delete (Long id);

    User findAndEnsureExist(Long id);

}