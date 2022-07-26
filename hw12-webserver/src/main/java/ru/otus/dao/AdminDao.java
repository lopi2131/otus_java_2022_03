package ru.otus.dao;

import ru.otus.model.Admin;

import java.util.Optional;

public interface AdminDao {

    Optional<Admin> findByLogin(String login);
}