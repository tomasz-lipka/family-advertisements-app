package pl.familyadvertisementsapp.controller.rest.errorhandling;

import org.springframework.http.HttpStatus;

import java.util.Date;

//TODO desc + where package
public record RestExceptionResponseEntity(String message, HttpStatus httpStatus, Date date) {
}
