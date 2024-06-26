package com.ampei.delichi.web.dtos.responses;

import com.ampei.delichi.persistance.models.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantResponse {

    private Long id;

    private String name;

    private String address;

    private String schedule;

    private String kitchen;

    private Long phoneNumber;

    private List<ReviewResponse> reviewResponseList;

}