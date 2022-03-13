package com.apiProdutosBlack.apiProdutosBlack.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
        ErrorMessage erro = new ErrorMessage(new Date(), "Erro: "+ ex.getLocalizedMessage());
		return new ResponseEntity<>(erro, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage erro = new ErrorMessage();
		erro.setMessage("O método usado '"+ex.getMethod()+"' não é suportado para esta operação, verifique novamente!");
		erro.setCurrentDate(new Date());
		return new ResponseEntity<>(erro, new HttpHeaders(),status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage erro = new ErrorMessage();
		erro.setMessage("Tipo de midia "+ex.getContentType()+" não aceita, utilize JSON");
		erro.setCurrentDate(new Date());
		return new ResponseEntity<>(erro, new HttpHeaders(),status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage erro = new ErrorMessage();
		erro.setMessage("Tipo de midia não suportada, utilize essa lista de opções: "+ex.getSupportedMediaTypes());
		erro.setCurrentDate(new Date());
		return new ResponseEntity<>(erro, new HttpHeaders(),status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage erro = new ErrorMessage();
		erro.setMessage("Causa: "+ex.getCause());
		erro.setCurrentDate(new Date());
		return new ResponseEntity<>(erro, new HttpHeaders(),status);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		ErrorMessage erro = new ErrorMessage();
		erro.setMessage("Tempo expirado de requisição, verifique o servidor.");
		erro.setCurrentDate(new Date());
		return new ResponseEntity<>(erro, new HttpHeaders(),status);
	}
	
	
}