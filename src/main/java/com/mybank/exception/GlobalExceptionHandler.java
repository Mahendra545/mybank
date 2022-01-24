package com.mybank.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mybank.dto.ErrorResponse;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleExceptio(DataNotFoundException ex){
		ErrorResponse validationErrorResponse = new ErrorResponse();
		validationErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		validationErrorResponse.setMessage(ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<>(validationErrorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleExceptio(EntityNotFoundException ex){
		ErrorResponse validationErrorResponse = new ErrorResponse();
		validationErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		validationErrorResponse.setMessage("Table is not existing in database");
		logger.error("Table is not existing in database");
		return new ResponseEntity<>(validationErrorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleExceptio(SQLIntegrityConstraintViolationException ex){
		ErrorResponse validationErrorResponse = new ErrorResponse();
		validationErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Data is already existing with given details");
		logger.error("Data is already existing with given details");
		return new ResponseEntity<>(validationErrorResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleExceptio(MethodArgumentTypeMismatchException ex){
		ErrorResponse validationErrorResponse = new ErrorResponse();
		validationErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Please enter a valid input");
		logger.error("Please enter a valid input");
		return new ResponseEntity<>(validationErrorResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ObjNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleObjNotFoundException(ObjNotFoundException ex){
		logger.error("Exception occured with "+ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(400);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MyBankException.class)
	public ResponseEntity<Object> handleMyBankException(MyBankException ex) {
		logger.error("Exception occured with"+ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(500);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @ExceptionHandler(BeneficiaryException.class)
    public ResponseEntity<ErrorResponse> handleExceptio(BeneficiaryException ex){
        ErrorResponse validationErrorResponse = new ErrorResponse();
        validationErrorResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        validationErrorResponse.setMessage(ex.getMessage());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(validationErrorResponse,HttpStatus.NOT_ACCEPTABLE);
    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InvalidUserCredentials.class)
	public ResponseEntity<ErrorResponse> handleExceptio(InvalidUserCredentials ex){
		ErrorResponse validationErrorResponse = new ErrorResponse();
		validationErrorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		validationErrorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<>(validationErrorResponse,HttpStatus.UNAUTHORIZED);
	}
	
	
 
}
