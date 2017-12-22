package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.ConnectionRepository;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GenericDAO;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    protected ConnectionRepository connectionRepository = ConnectionRepository.getInstance();

    @Override
    public List<T> readAll() {
        List<T> result = null;
        try (Connection connection = connectionRepository.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getRequest(Method.READ_ALL))) {
            result = createList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T read(T t) {
        T result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest(Method.READ))) {
            setParameters(Method.READ, statement, t, null);
            resultSet = statement.executeQuery();
            result = createResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public T readById(Long id) {
        T result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest(Method.READ_BY_ID))) {
            setParameters(Method.READ_BY_ID, statement, null, id);
            resultSet = statement.executeQuery();
            result = createResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void create(T t) {
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest(Method.CREATE))) {
            setParameters(Method.CREATE, statement, t, null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T t) {
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest(Method.UPDATE))) {
            setParameters(Method.UPDATE, statement, t, null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest(Method.DELETE))) {
            setParameters(Method.DELETE, statement, null, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getRequest(Method method);

    protected abstract List<T> createList(ResultSet resultSet) throws SQLException;

    protected abstract void setParameters(Method method, PreparedStatement statement, T t, Long id) throws SQLException;

    protected abstract T createResult(ResultSet resultSet) throws SQLException;
}
