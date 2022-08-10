package ru.neoflex.educationplatform.exception;

import org.openapitools.model.ErrorMessageDto;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

  @Bean
  public ErrorAttributes errorAttributes() {
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults().excluding(ErrorAttributeOptions.Include.EXCEPTION));
      }
    };
  }

  @ExceptionHandler(CustomException.class)
  public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
    res.sendError(HttpStatus.FORBIDDEN.value(), "Доступ запрещен");
  }

  @ExceptionHandler(Exception.class)
  public void handleException(HttpServletResponse res) throws IOException {
    res.sendError(HttpStatus.BAD_REQUEST.value(), "Что то пошло не так");
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorMessageDto handlerIllegalArgumentException (IllegalArgumentException e){
    return ErrorMessageDto.builder()
            .message(e.getMessage())
            .build();
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EntityNotFoundException.class)
  public ErrorMessageDto handlerEntityNotFoundException (EntityNotFoundException e){
    return ErrorMessageDto.builder()
            .message(e.getMessage())
            .build();
  }
}
