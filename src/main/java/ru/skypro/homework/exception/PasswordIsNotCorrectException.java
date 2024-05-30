package ru.skypro.homework.exception;

public class PasswordIsNotCorrectException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Current password is not correct :(";
    }
}
