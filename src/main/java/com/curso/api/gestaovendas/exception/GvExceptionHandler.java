package com.curso.api.gestaovendas.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GvExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = getListErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> getListErrors(BindingResult bindingResult){
        List<Error> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUser = MsgErrorUser(fieldError);
            String msgDev = fieldError.toString();
            errors.add(new Error(msgUser, msgDev));
        });
        return errors;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgUser = "Recurso não encontrado";
        String msgDev = ex.toString();
        List<Error> errors = Arrays.asList(new Error(msgUser, msgDev));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String MsgErrorUser(FieldError fieldError){
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres",
                    fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }
        return fieldError.getField();
    }
}
