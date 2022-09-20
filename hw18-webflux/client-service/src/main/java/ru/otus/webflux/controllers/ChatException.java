package ru.otus.webflux.controllers;

public class ChatException extends RuntimeException {
    public ChatException(String message) {
        super(message);
    }
}
