package br.com.pagamento.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pagamento.dto.ErrorMessageDTO;
import br.com.pagamento.exception.LimiteUltrapassadoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice 
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
	    List<ErrorMessageDTO> dto = new ArrayList<>();
	    ex.getBindingResult().getFieldErrors().forEach( e -> {
	        String message = messageSource.getMessage(e , LocaleContextHolder.getLocale());
	        System.out.println("Error message: " + message);
	        ErrorMessageDTO error = new ErrorMessageDTO(e.getField(), message);
	        dto.add(error);
	    });	    

	    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<Object> handleConstraintViolation(HttpServletRequest req, ConstraintViolationException ex) {
		ErrorMessageDTO errorInfo = new ErrorMessageDTO(req.getRequestURI().toString() , ex.getMessage());
	    return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LimiteUltrapassadoException.class)
	@ResponseBody
	public ResponseEntity<Object> handleLimiteUltrapassadoException(HttpServletRequest req, LimiteUltrapassadoException ex) {
		ErrorMessageDTO errorInfo = new ErrorMessageDTO(req.getRequestURI().toString() , ex.getMessage());
	    return new ResponseEntity<>(errorInfo, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
