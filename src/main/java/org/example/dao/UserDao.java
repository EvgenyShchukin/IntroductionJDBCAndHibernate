package org.example.dao;

import org.example.bean.User;

import java.util.List;

public interface UserDao {
    void createUserTable ();
    void dropUserTable ();
    void saveUserTable (String name, String lastname, Byte age);
    void removeUserById (Long id);
    List<User> getAllUsers ();
    void clearUserTable ();
}
