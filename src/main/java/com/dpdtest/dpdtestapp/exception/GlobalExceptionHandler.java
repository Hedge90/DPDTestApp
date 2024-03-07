package com.dpdtest.dpdtestapp.exception;

import com.dpdtest.dpdtestapp.DTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {

        return new ResponseEntity<>(new ErrorDTO("error", "Invalid JSON format or payload structure"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorDTO> handleInvalidParameterException(InvalidParameterException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}