package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.SqlRequestManager;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GenericDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.NewsInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.repository.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl extends AbstractDAO<News> implements NewsInterface {

    private GenericDAO<User> userDAO = UserDAOImpl.getInstance();

    private static volatile NewsDAOImpl instance;

    private NewsDAOImpl() {
    }

    public static synchronized NewsDAOImpl getInstance() {
        if (instance == null) {
            instance = new NewsDAOImpl();
        }
        return instance;
    }

    @Override
    protected String getRequest(Method method) {
        return SqlRequestManager.getRequest(method.toString().toLowerCase() + ".news");
    }

    @Override
    protected List<News> createList(ResultSet resultSet) throws SQLException {
        List<News> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(getNews(resultSet));
        }
        return list;
    }

    @Override
    protected void setParameters(Method method, PreparedStatement statement, News news, Long id) throws SQLException {
        switch (method) {
            case READ_BY_ID:
                statement.setLong(1, id);
                break;
            case CREATE:
                statement.setLong(1, news.getUser().getId());
                statement.setString(2, news.getAuthor());
                statement.setString(3, news.getName());
                statement.setString(4, news.getDescription());
                statement.setTimestamp(5, news.getTimestamp());
                statement.setString(6, news.getImage());
                break;
            case UPDATE:
                statement.setLong(1, news.getUser().getId());
                statement.setString(2, news.getAuthor());
                statement.setString(3, news.getName());
                statement.setString(4, news.getDescription());
                statement.setTimestamp(5, news.getTimestamp());
                statement.setString(6, news.getImage());
                statement.setLong(7, news.getId());
                break;
            case DELETE:
                statement.setLong(1, id);
                break;
            case READ:
                statement.setString(1, news.getName());
            default:
                break;
        }
    }

    @Override
    protected News createResult(ResultSet resultSet) throws SQLException {
        News news = null;
        while (resultSet.next()) {
            news = getNews(resultSet);
        }
        return news;
    }

    private News getNews(ResultSet resultSet) throws SQLException {
        News news = News.newBuilder()
                .id(resultSet.getLong("id"))
                .user(userDAO.readById(resultSet.getLong("user")))
                .author(resultSet.getString("author"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .timestamp(resultSet.getTimestamp("date"))
                .image(resultSet.getString("image"))
                .build();
        return news;
    }
}
