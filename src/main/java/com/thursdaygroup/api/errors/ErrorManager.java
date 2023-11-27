package com.thursdaygroup.api.errors;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//La idea de esta clase es la gestión centralizada de errores: aquí se controla qué
//se devuelve en caso de que no haya un try-catch conteniendo la excepción lanzada
//en los services.
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class)
    //El ExceptionHandler filtra el tipo de excepción que se atiende en el método.
    public ResponseEntity treatError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Esta excepción se activa con las validations (anotaciones) de los DTO create y update.
    public ResponseEntity treatError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    //Devuelve lista de errores al validar el json (los @notNull, etc).
    //Se devuelve un DTO para legibilidad y control de info.

    @ExceptionHandler(ValidationException.class)
    //Esta excepción está pensada para ser lanzada manualmente cuando no se cumpla una regla de negocio.
    //No aplicada aún en ent1 a ent3...
    public ResponseEntity ValidationException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    //Esta excepción es genérica. Ej: un error de conexión a base de datos, un null pointer o un divided by zero
    //se atraparían aquí al no estar especificados como exception handlers.
    public ResponseEntity treatError500(Exception e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}

