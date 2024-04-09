package com.ampei.delichi.web.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private Object data;

    private String detail;

    private Boolean success;

    private Integer status;

    private HttpStatus httpStatus;

    public ResponseEntity<BaseResponse> apply() {
        return new ResponseEntity<>(this, httpStatus);
    }

}