package com.lloms.outlet_service.advisor;

import com.lloms.outlet_service.exception.NotFoundException;
import com.lloms.outlet_service.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvisorExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> HandleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Error",e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
