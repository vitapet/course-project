package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.SqlRequestManager;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GoodsInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.Goods;
import com.gmail.vitaliapetsenak.shop.repository.model.GoodsCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.GoodsIsDeleted;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl extends AbstractDAO<Goods> implements GoodsInterface {

    private static volatile GoodsDAOImpl instance = null;

    private GoodsDAOImpl() {
    }

    public static synchronized GoodsDAOImpl getInstance() {
        if (instance == null) {
            instance = new GoodsDAOImpl();
        }
        return instance;
    }

    @Override
    protected String getRequest(Method method) {
        return SqlRequestManager.getRequest(method.toString().toLowerCase() + ".goods");
    }

    private String getRequest(String type) {
        return SqlRequestManager.getRequest(type + ".goods");
    }

    @Override
    protected List<Goods> createList(ResultSet resultSet) throws SQLException {
        List<Goods> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(getGoods(resultSet));
        }
        return list;
    }

    @Override
    protected void setParameters(Method method, PreparedStatement statement, Goods goods, Long id) throws SQLException {
        switch (method) {
            case READ_BY_ID:
                statement.setLong(1, id);
                break;
            case CREATE:
                statement.setString(1, goods.getName());
                statement.setString(2, goods.getDescription());
                statement.setString(3, goods.getCategory().toString());
                statement.setLong(4, goods.getCount());
                statement.setString(6, goods.getIsDeleted().toString());
                statement.setBigDecimal(5, goods.getPrice());
                break;
            case UPDATE:
                statement.setString(1, goods.getName());
                statement.setString(2, goods.getDescription());
                statement.setString(3, goods.getCategory().toString());
                statement.setLong(4, goods.getCount());
                statement.setBigDecimal(5, goods.getPrice());
                statement.setString(6, goods.getIsDeleted().toString());
                statement.setLong(7, goods.getId());
                break;
            case DELETE:
                statement.setLong(1, id);
                break;
            default:
                break;
        }
    }

    @Override
    protected Goods createResult(ResultSet resultSet) throws SQLException {
        Goods goods = null;
        while (resultSet.next()) {
            goods = getGoods(resultSet);
        }
        return goods;
    }

    private Goods getGoods(ResultSet resultSet) throws SQLException {
        Goods goods = Goods.newBuilder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .category(GoodsCategory.valueOf(resultSet.getString("category")))
                .count(resultSet.getInt("count"))
                .price(resultSet.getBigDecimal("price"))
                .isDeleted(GoodsIsDeleted.valueOf(resultSet.getString("is_deleted")))
                .build();
        return goods;
    }

    @Override
    public List<Goods> getByCategory(GoodsCategory category, GoodsIsDeleted isDeleted) {
        List<Goods> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_by_category"))) {
            statement.setString(1, category.toString());
            statement.setString(2, isDeleted.toString());
            resultSet = statement.executeQuery();
            result = createList(resultSet);
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
    public List<Goods> getByCategory(GoodsCategory category) {
        List<Goods> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_by_category_all"))) {
            statement.setString(1, category.toString());
            resultSet = statement.executeQuery();
            result = createList(resultSet);
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
    public List<Goods> getNotDeleted() {
        List<Goods> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_not_deleted"))) {
            statement.setString(1, GoodsIsDeleted.NOT_DELETED.toString());
            resultSet = statement.executeQuery();
            result = createList(resultSet);
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
}
