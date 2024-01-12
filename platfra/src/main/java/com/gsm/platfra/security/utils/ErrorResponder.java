package com.gsm.platfra.security.utils;

import com.gsm.platfra.exception.response.ErrorResponse;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(status, message);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value()); // status 작성
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }


}
