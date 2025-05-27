package com.cts.bookmanagement.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundexception(ResourceNotFoundException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(true),
				"USER_NOT_FOUND"
				);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> emailAlreadyExistsException(EmailAlreadyExistsException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"EMAIL_ALREADY_EXISTS"
				);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserNameAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> userAlreadyExistsException(UserNameAlreadyExistsException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"USER_NAME_ALREADY_EXISTS"
				);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> Exception(Exception exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INTERNAL SERVER ERROR"
				);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Object> handleConstraintViolationException(
	            ConstraintViolationException ex, WebRequest request) {

	        // Collect all constraint violations into a map (property path -> error message)
	        Map<String, String> errors = ex.getConstraintViolations().stream()
	                .collect(Collectors.toMap(
	                        // Extract the field name from the property path (e.g., "getUser.id" -> "id")
	                        violation -> {
	                            String path = violation.getPropertyPath().toString();
	                            int lastDotIndex = path.lastIndexOf('.');
	                            return lastDotIndex != -1 ? path.substring(lastDotIndex + 1) : path;
	                        },
	                        ConstraintViolation::getMessage // Value: the error message for the violation
	                ));

	        // Return a ResponseEntity with the errors map and a 400 Bad Request status
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	
	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//			
//		Map<String, String> errors = new HashMap<>();
//		
//		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
//		
//		errorList.forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//		
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}
	
	
}
