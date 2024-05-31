package ru.skypro.homework.exception;

public class AdNotFoundException extends RuntimeException {
    private final Integer id;

    public AdNotFoundException(Integer id) {
        this.id = id;
    }
    @Override
    public String getMessage() {
        return "Ad with id: " + id + " is not found!";
    }
}
