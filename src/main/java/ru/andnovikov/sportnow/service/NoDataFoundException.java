package ru.andnovikov.sportnow.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such element")
public class NoDataFoundException extends RuntimeException  {

    public NoDataFoundException() {}
}
