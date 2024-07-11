package ar.com.demo.dux.handler;

import ar.com.demo.dux.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @Nullable
    public ResponseEntity<Object> handlerValidationException(final MethodArgumentNotValidException exception,
                                                                  final WebRequest request) {

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError: fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ErrorDTO.builder().codigo(400).mensaje("La solicitud es invalida: ".concat(errors.toString())).build(),
                HttpStatus.BAD_REQUEST);
    }

}
