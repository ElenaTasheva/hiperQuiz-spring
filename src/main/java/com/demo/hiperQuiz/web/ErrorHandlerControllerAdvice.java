package com.demo.hiperQuiz.web;

import com.demo.hiperQuiz.dto.ErrorResponse;
import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.exception.InvalidEntityDataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(basePackageClasses = UserController.class )
public class ErrorHandlerControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(
                new ErrorResponse(NOT_FOUND.value(), e.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityConstraintViolations(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(NOT_FOUND).body(
                new ErrorResponse(NOT_FOUND.value(), ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDbConstraintViolations(DataIntegrityViolationException ex) {
        Throwable cause = ex;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return ResponseEntity.badRequest().body(
                new ErrorResponse(BAD_REQUEST.value(), cause.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDbConstraintViolations(InvalidEntityDataException ex) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(BAD_REQUEST.value(), ex.getMessage())
        );
    }
}
