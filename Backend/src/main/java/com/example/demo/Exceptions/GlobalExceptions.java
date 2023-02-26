package com.example.demo.Exceptions;

import com.example.demo.Payloads.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundHandler(
    ResourceNotFoundException ex
  ) {
    String msg = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(msg, false);
    return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TransactionSystemException.class)
  public ResponseEntity<ApiResponse> handleTransactionSystemException(
    TransactionSystemException ex
  ) {
    String msg = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(msg, false);

    return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodExcception(
    MethodArgumentNotValidException ex
  ) {
    Map<String, String> res = new HashMap<>();
    ex
      .getBindingResult()
      .getAllErrors()
      .forEach(
        error -> {
          String fieldName = ((FieldError) error).getField();
          String message = error.getDefaultMessage();
          res.put(fieldName, message);
        }
      );
    return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
  }
}
