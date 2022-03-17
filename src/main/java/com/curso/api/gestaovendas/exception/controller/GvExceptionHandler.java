package com.curso.api.gestaovendas.exception.controller;

import com.curso.api.gestaovendas.exception.model.MessagesError;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import org.springframework.dao.DataIntegrityViolationException;
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
    private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";
    private static final String CONSTANT_VALIDATION_MIN = "Min";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<MessagesError> messagesErrors = getListErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, messagesErrors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<MessagesError> getListErrors(BindingResult bindingResult){
        List<MessagesError> messagesErrors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUser = MsgErrorUser(fieldError);
            String msgDev = fieldError.toString();
            messagesErrors.add(new MessagesError(msgUser, msgDev));
        });
        return messagesErrors;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgUser = "Recurso não encontrado";
        String msgDev = ex.toString();
        List<MessagesError> messagesErrors = Arrays.asList(new MessagesError(msgUser, msgDev));
        return handleExceptionInternal(ex, messagesErrors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUser = "Recurso não encontrado";
        String msgDev = ex.toString();
        List<MessagesError> messagesErrors = Arrays.asList(new MessagesError(msgUser, msgDev));
        return handleExceptionInternal(ex, messagesErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request){
        String msgUser = ex.getMessage();
        String msgDev = ex.getMessage();
        List<MessagesError> messagesErrors = Arrays.asList(new MessagesError(msgUser, msgDev));
        return handleExceptionInternal(ex, messagesErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String MsgErrorUser(FieldError fieldError){
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres",
                    fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_MIN)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ser maior que %s",
                    fieldError.getArguments()[1]));
        }
        return fieldError.getField();
    }
}
