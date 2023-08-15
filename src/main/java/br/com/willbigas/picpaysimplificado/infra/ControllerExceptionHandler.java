package br.com.willbigas.picpaysimplificado.infra;

import br.com.willbigas.picpaysimplificado.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity threatDuplicateEntity(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", String.valueOf(HttpStatus.BAD_REQUEST.value()));
		return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threatEntityNotFound(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity threatGeneralException(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		return ResponseEntity.internalServerError().body(exceptionDTO);
	}
}
