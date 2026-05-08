package sissa.NotaZ.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> recursoNaoEncontrado(ResourceNotFoundException e, HttpServletRequest request){
        String error = "ResourceNotFoundException";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> emailDuplicado(DatabaseException e, HttpServletRequest request){
        String error = "DatabaseException";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validacaoInvalida(MethodArgumentNotValidException e, HttpServletRequest request){
        String error = "MethodArgumentNotValidException";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagem = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        StandardError err = new StandardError(Instant.now(), status.value(), error, mensagem, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
