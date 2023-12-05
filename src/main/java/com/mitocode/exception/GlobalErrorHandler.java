package com.mitocode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice///AÑADIR LA ANOTACION CONTROLLER PARA QUE SPRING SEPA QUE ES UNA CLASE QUE SE UTILIZA PARA MANEJAR EXCEPCIONES
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    //SE ESTÁ MANEJANDO LA CLASE "EXCEPTION" QUE ES LA CLASE EXCEPTION GENERAL POR SI SALTA UN EXCEPCIÓN NO MAPEADA O CONTROLADA
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerAllException(Exception exc, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exc.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //SE ESTÁ
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerModelNotFoundException(ModelNotFoundException exc, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exc.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handlerSqlException(ModelNotFoundException exc, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exc.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    //PARA AGREGAR UNA EXCEPTION CLICK DERECHO GENERATE SOBRESCRIBIR UN MÉTODO DE ResponseEntityExceptionHandler


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        //Se está utilizando la función MAP para iterar y hacer una modificación para extraer el mensaje *CUANDO APLICAS UN STREAM Y UN OPERADOR ESTE DEVOLVERÁ UN STRING*
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())                  //Para cada error se va a obtener su campo que ocasiono el error
                                                                                                    //Y de este mismo error su mensaje por defecto
                .collect(Collectors.joining(". "));                                        //Normalmente, se recolecta una lista, pero como se recolectará todo como un string
                                                                                                //se utilizará el *.JOINING* el cual hace que todas las cadenas de texto que iteren se unan
                                                                                              //Se puede utlizar un delimitador como espacio o coma

        /*String message ="";
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            message += error.getField() + " : " + error.getDefaultMessage() + " ";
        }*/


        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), message, req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);   //CUANDO TU SE MANDA UNA ESTRUCTURA QUE CORROMPE EL TEMA DE LAS RESTRICCIONES  SE TIENE QUE
    }                                                               //DEVOLVER UN BAD_REQUEST 400
}


