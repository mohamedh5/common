package com.iter.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.amadeus.exceptions.ClientException;
import com.amadeus.exceptions.ResponseException;

@RestControllerAdvice
public class GeneralErrorHandler {

	private final Logger logger = LoggerFactory.getLogger(GeneralErrorHandler.class);
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ErrorDto> httpClientErrorExceptionHandler(HttpClientErrorException ex) {
		logger.warn("HttpClientErrorException: {0}", ex);
		ErrorDto error = new ErrorDto();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Invalid request");
		error.setDescription(ex.getResponseBodyAsString());

		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ErrorDto> httpClientExceptionHandler(ClientException ex) {
		logger.warn("ClientException: {0}", ex);
		ErrorDto error = new ErrorDto();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Invalid request");
		error.setDescription(ex.getDescription());
		
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<ErrorDto> httpServerErrorExceptionHandler(HttpServerErrorException ex) {
		logger.warn("HttpServerErrorException: {0}", ex);

		ErrorDto error = new ErrorDto();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("a problem occured while invoking the external API");
		error.setDescription(ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(ResponseException.class)
	public ResponseEntity<ErrorDto> httpResponseExceptionHandler(ResponseException ex) {
		logger.warn("ResponseException: {0}", ex);
		
		ErrorDto error = new ErrorDto();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("a problem occured while invoking the external API");
		error.setDescription(ex.getDescription());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
