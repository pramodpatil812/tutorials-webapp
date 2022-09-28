package com.example.tutorials.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String errorCode;
    private String message;
    Object body;

    public static ApiResponse success(Object body, String message) {
        return new ApiResponse("0", message, body);
    }

    public static ApiResponse success(Object body) {
        return new ApiResponse(null, null, body);
    }

    public static ApiResponse success() {
        return new ApiResponse(null, null, null);
    }

    public static ApiResponse failure(String errorCode, String message, Object body) {
        return new ApiResponse(errorCode, message, body);
    }

    public static ApiResponse failure(String errorCode, String message) {
        return new ApiResponse(errorCode, message, null);
    }
}
