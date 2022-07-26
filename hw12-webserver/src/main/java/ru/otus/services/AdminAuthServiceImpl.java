package ru.otus.services;

import ru.otus.dao.AdminDao;

public class AdminAuthServiceImpl implements AuthService {

    private final AdminDao adminDao;

    public AdminAuthServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return adminDao.findByLogin(login)
                .map(admin -> admin.getPassword().equals(password))
                .orElse(false);
    }

}
