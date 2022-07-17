package ru.otus.services;

public interface AuthService {
    boolean authenticate(String login, String password);
}
