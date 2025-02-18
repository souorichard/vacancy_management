package org.richard.vacancy_management.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    List<ErrorMessageDTO> dto = new ArrayList<>();

    exception.getBindingResult().getFieldErrors().forEach(error -> {
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

      ErrorMessageDTO errorMessage = new ErrorMessageDTO(message,error.getField());

      dto.add(errorMessage);
    });

    return ResponseEntity.status(400).body(dto);
  }

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }
}
