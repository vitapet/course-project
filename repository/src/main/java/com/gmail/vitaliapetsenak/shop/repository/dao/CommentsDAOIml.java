package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.SqlRequestManager;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.CommentsInterface;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GenericDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Comment;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentsDAOIml extends AbstractDAO<Comment> implements CommentsInterface {

    private GenericDAO<User> userDAO = UserDAOImpl.getInstance();
    private GenericDAO<News> newsDAO = NewsDAOImpl.getInstance();

    private static volatile CommentsDAOIml instance;

    private CommentsDAOIml() {
    }

    public static synchronized CommentsDAOIml getInstance() {
        if (instance == null) {
            instance = new CommentsDAOIml();
        }
        return instance;
    }

    @Override
    protected String getRequest(Method method) {
        return SqlRequestManager.getRequest(method.toString().toLowerCase() + ".comment");
    }

    private String getRequest(String type) {
        return SqlRequestManager.getRequest(type + ".comment");
    }

    @Override
    protected List<Comment> createList(ResultSet resultSet) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        while (resultSet.next()) {
            comments.add(getComment(resultSet));
        }
        return comments;
    }

    @Override
    protected void setParameters(Method method, PreparedStatement statement, Comment comment, Long id) throws SQLException {
        switch (method) {
            case READ_BY_ID:
                statement.setLong(1, id);
                break;
            case CREATE:
                statement.setString(1, comment.getText());
                statement.setLong(2, comment.getUser().getId());
                statement.setLong(3, comment.getNews().getId());
                statement.setTimestamp(4, comment.getDate());
                break;
            case UPDATE:
                statement.setString(1, comment.getText());
                statement.setLong(2, comment.getUser().getId());
                statement.setLong(3, comment.getNews().getId());
                statement.setTimestamp(4, comment.getDate());
                statement.setLong(5, comment.getId());
                break;
            case DELETE:
                statement.setLong(1, id);
                break;
            default:
                break;
        }
    }

    @Override
    protected Comment createResult(ResultSet resultSet) throws SQLException {
        Comment comment = null;
        while (resultSet.next()) {
            comment = getComment(resultSet);
        }
        return comment;
    }

    private Comment getComment(ResultSet resultSet) throws SQLException {
        Comment comment = Comment.newBuilder()
                .id(resultSet.getLong("id"))
                .text(resultSet.getString("text"))
                .user(userDAO.readById(resultSet.getLong("user")))
                .news(newsDAO.readById(resultSet.getLong("news")))
                .date(resultSet.getTimestamp("date"))
                .build();
        return comment;
    }

    @Override
    public List<Comment> getNewsComments(Long newsId) {
        List<Comment> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_for_news"))) {
            statement.setLong(1, newsId);
            resultSet = statement.executeQuery();
            result = createList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void deleteNewsComments(Long newsId) {
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("delete_for_news"))) {
            statement.setLong(1, newsId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
