package com.ampei.delichi.services.interfaces;

import com.ampei.delichi.web.dtos.requests.*;
import com.ampei.delichi.web.dtos.responses.BaseResponse;

public interface IReservationService {

    BaseResponse get(Long id);

    BaseResponse list(Long id);

    BaseResponse create(CreateReservationRequest request, Long idUser, Long idRestaurant);

    BaseResponse update(UpdateReservationRequest request, Long id);

    BaseResponse delete (Long id);

}