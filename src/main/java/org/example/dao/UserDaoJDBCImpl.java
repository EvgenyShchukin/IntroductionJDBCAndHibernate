package org.example.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.bean.User;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getJDBConnection();

    @Override
    public void createUserTable() {
        String sql = "create table IF NOT EXISTS user (" +
                "id BIGINT primary key NOT NULL AUTO_INCREMENT, name VARCHAR(45), " +
                "lastName VARCHAR(45), " +
                "age TINYINT(3))";
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUserTable() {
        String sql = "DROP TABLE user";
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUserTable(String name, String lastname, Byte age) {
        String sql = "INSERT INTO  user (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(Long id) {
        String sql = "DELETE from user where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void clearUserTable() {
        String sql = "TRUNCATE user";
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
