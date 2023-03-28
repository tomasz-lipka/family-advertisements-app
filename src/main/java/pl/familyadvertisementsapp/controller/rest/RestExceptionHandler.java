package pl.familyadvertisementsapp.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.familyadvertisementsapp.exception.RestAdvertisementControllerException;

import java.util.Date;

//TODO refactor + package
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RestAdvertisementControllerException.class})
    public ResponseEntity<Object> handleRestAdvertisementControllerException(RestAdvertisementControllerException e) {
        RestExceptionResponseEntity restExceptionResponseEntity = new RestExceptionResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST, new Date());
        return new ResponseEntity<>(restExceptionResponseEntity, HttpStatus.BAD_REQUEST);
    }
}
