package com.ojt.kbl.exception;

public class InvalidEmailException extends SimpleBankingGlobalException {
    public InvalidEmailException(String message, String code) {
        super(message, code);
    }
}
