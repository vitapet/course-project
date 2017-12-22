package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.SqlRequestManager;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.UserInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.Role;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> implements UserInterface {

    private static volatile UserDAOImpl instance;

    private UserDAOImpl() {
    }

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    protected String getRequest(Method method) {
        return SqlRequestManager.getRequest(method.toString().toLowerCase() + ".user");
    }

    @Override
    protected List<User> createList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(getUser(resultSet));
        }
        return users;
    }

    @Override
    protected void setParameters(Method method, PreparedStatement statement, User user, Long id) throws SQLException {
        switch (method) {
            case CREATE:
                setStatementParams(statement, user);
                break;
            case UPDATE:
                setStatementParams(statement, user);
                statement.setLong(15, user.getId());
                break;
            case DELETE:
                statement.setLong(1, id);
                break;
            case READ_BY_ID:
                statement.setLong(1, id);
                break;
            case READ:
                statement.setString(1, user.getLogin());
                break;
            default:
                break;
        }
    }

    @Override
    protected User createResult(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = getUser(resultSet);
        }
        return user;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = User.newBuilder()
                .id(resultSet.getLong("id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role")))
                .firstName(resultSet.getString("first_name"))
                .surname(resultSet.getString("surname"))
                .birthDate(resultSet.getDate("birth_date"))
                .phone(resultSet.getString("phone"))
                .country(resultSet.getString("country"))
                .city(resultSet.getString("city"))
                .street(resultSet.getString("street"))
                .house(resultSet.getString("house"))
                .block(resultSet.getInt("block"))
                .apartment(resultSet.getInt("apartment"))
                .status(UserStatus.valueOf(resultSet.getString("status")))
                .build();
        return user;
    }

    private void setStatementParams(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole().toString());
        statement.setString(4, user.getFirstName());
        statement.setString(5, user.getSurname());
        statement.setDate(6, user.getBirthDate());
        statement.setString(7, user.getPhone());
        statement.setString(8, user.getCountry());
        statement.setString(9, user.getCity());
        statement.setString(10, user.getStreet());
        statement.setString(11, user.getHouse());
        statement.setInt(12, user.getBlock());
        statement.setInt(13, user.getApartment());
        statement.setString(14, user.getStatus().toString());
    }
}
