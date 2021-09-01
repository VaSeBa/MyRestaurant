package ru.vaseba.myrestaurant.error;

public class DeadLineForVoicingException extends RuntimeException {
    public DeadLineForVoicingException(String message) {
        super(message);
    }
}
