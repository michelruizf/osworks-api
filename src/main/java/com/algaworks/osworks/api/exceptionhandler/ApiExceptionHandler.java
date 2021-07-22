package com.algaworks.osworks.api.exceptionhandler;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var customExceptionInfo = new CustomExceptionInfo();
		customExceptionInfo.setStatus(status.value());
		customExceptionInfo.setMensagem(ex.getMessage());
		customExceptionInfo.setDataHora(OffsetDateTime.now());

		return handleExceptionInternal(ex, customExceptionInfo, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		var customExceptionInfo = new CustomExceptionInfo();
		customExceptionInfo.setStatus(status.value());
		customExceptionInfo.setMensagem(ex.getMessage());
		customExceptionInfo.setDataHora(OffsetDateTime.now());

		return handleExceptionInternal(ex, customExceptionInfo, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
			return new Campo(
				((FieldError) objectError).getField(),
				messageSource.getMessage(objectError, LocaleContextHolder.getLocale())
			);
		}).collect(Collectors.toList());

		var customExceptionInfo = new CustomExceptionInfo();
		customExceptionInfo.setStatus(status.value());
		customExceptionInfo.setMensagem("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		customExceptionInfo.setDataHora(OffsetDateTime.now());
		customExceptionInfo.setCampos(campos);

		return super.handleExceptionInternal(ex,customExceptionInfo, headers, status, request);
	}
}
