package com.ampei.delichi.services.interfaces;

import com.ampei.delichi.persistance.models.Restaurant;
import com.ampei.delichi.web.dtos.requests.CreateRestaurantRequest;
import com.ampei.delichi.web.dtos.requests.UpdateRestaurantRequest;
import com.ampei.delichi.web.dtos.responses.BaseResponse;

public interface IRestaurantService {

    BaseResponse get(Long id);

    BaseResponse list();

    BaseResponse listRestaurantsByName(String name);

    BaseResponse create(CreateRestaurantRequest request);

    BaseResponse update(UpdateRestaurantRequest request, Long id);

    BaseResponse delete(Long id);

    Restaurant findAndEnsureExist(Long id);

}