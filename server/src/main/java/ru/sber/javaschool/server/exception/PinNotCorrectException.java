package ru.sber.javaschool.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

public class PinNotCorrectException extends RuntimeException {
    public PinNotCorrectException() {
        super();
    }

    public PinNotCorrectException(String message) {
        super(message);
    }

    public PinNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinNotCorrectException(Throwable cause) {
        super(cause);
    }

    protected PinNotCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
