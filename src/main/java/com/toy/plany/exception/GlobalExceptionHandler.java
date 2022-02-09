package com.toy.plany.exception;

import com.toy.plany.exception.exceptions.DepartmentNotFoundException;
import com.toy.plany.exception.exceptions.IncorrectPasswordException;
import com.toy.plany.exception.exceptions.InsufficientColorException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectPasswordException.class)
    protected ResponseEntity<ErrorResponse> IncorrectPasswordExceptionHandler(
            IncorrectPasswordException e) {
        final ErrorResponse response = ErrorResponse.from(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> UserNotFoundExceptionHandler(
            UserNotFoundException e) {
        final ErrorResponse response = ErrorResponse.from(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientColorException.class)
    protected ResponseEntity<ErrorResponse> InsufficientColorExceptionHandler(
            InsufficientColorException e) {
        final ErrorResponse response = ErrorResponse.from(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    protected ResponseEntity<ErrorResponse> DepartmentNotFoundExceptionHandler(
            DepartmentNotFoundException e) {
        final ErrorResponse response = ErrorResponse.from(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> InvalidInputExceptionHandler(
            SQLIntegrityConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.from(ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
