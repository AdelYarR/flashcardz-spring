package io.github.adelyarr.semester.exception;

public class EmailTakenException extends RuntimeException {

    public EmailTakenException(String message) {
        super(message);
    }
}
