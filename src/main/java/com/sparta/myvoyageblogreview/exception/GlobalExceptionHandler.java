package com.sparta.myvoyageblogreview.exception;

import com.sparta.myvoyageblogreview.common.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 에러 처리
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
	}
}