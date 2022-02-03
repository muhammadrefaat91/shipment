package com.springcard.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(ResourceDoesnotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceDoesnotExist ex) {
    	logger.error("Resource does not exist, " + ex.getCode() + ", "+ ex.getMessage());
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(ex.getCode());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionResponse> customException(SystemException ex) {
    	logger.error(ex.getErrorCode() + ", "+ ex.getMessage());
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(ex.getErrorCode().name());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        if (ex.getErrorCode() == SystemException.ErrorCode.NOT_UNIQUE)
            return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> globleExcpetionHandler(Exception ex,
                                                                    WebRequest request) {
    	logger.error("Unexpected Exception thrown, {}" , ex);
          ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
                request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
