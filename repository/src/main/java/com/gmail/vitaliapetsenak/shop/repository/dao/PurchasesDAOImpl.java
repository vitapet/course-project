package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.SqlRequestManager;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GenericDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.PurchasesInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.Goods;
import com.gmail.vitaliapetsenak.shop.repository.model.Purchases;
import com.gmail.vitaliapetsenak.shop.repository.model.PurchasesStatus;
import com.gmail.vitaliapetsenak.shop.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchasesDAOImpl extends AbstractDAO<Purchases> implements PurchasesInterface {

    private GenericDAO<User> userDAO = UserDAOImpl.getInstance();
    private GenericDAO<Goods> goodsDAO = GoodsDAOImpl.getInstance();

    private static volatile PurchasesDAOImpl instance;

    private PurchasesDAOImpl() {
    }

    public static synchronized PurchasesDAOImpl getInstance() {
        if (instance == null) {
            instance = new PurchasesDAOImpl();
        }
        return instance;
    }

    @Override
    protected String getRequest(Method method) {
        return SqlRequestManager.getRequest(method.toString().toLowerCase() + ".purchases");
    }

    private String getRequest(String type) {
        return SqlRequestManager.getRequest(type + ".purchases");
    }

    @Override
    protected List<Purchases> createList(ResultSet resultSet) throws SQLException {
        List<Purchases> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(getPurchases(resultSet));
        }
        return list;
    }

    @Override
    protected void setParameters(Method method, PreparedStatement statement, Purchases purchases, Long id) throws SQLException {
        switch (method) {
            case READ_BY_ID:
                statement.setLong(1, id);
                break;
            case CREATE:
                statement.setLong(1, purchases.getGoods().getId());
                statement.setLong(2, purchases.getCount());
                statement.setLong(3, purchases.getUser().getId());
                statement.setTimestamp(4, purchases.getTimestamp());
                statement.setString(5, purchases.getStatus().toString());
                statement.setBigDecimal(6, purchases.getAmount());
                break;
            case UPDATE:
                statement.setLong(1, purchases.getGoods().getId());
                statement.setLong(2, purchases.getCount());
                statement.setLong(3, purchases.getUser().getId());
                statement.setTimestamp(4, purchases.getTimestamp());
                statement.setString(5, purchases.getStatus().toString());
                statement.setBigDecimal(6, purchases.getAmount());
                statement.setLong(7, purchases.getId());
                break;
            case DELETE:
                statement.setLong(1, id);
                break;
            default:
                break;
        }
    }

    @Override
    protected Purchases createResult(ResultSet resultSet) throws SQLException {
        Purchases purchases = null;
        while (resultSet.next()) {
            purchases = getPurchases(resultSet);
        }
        return purchases;
    }

    private Purchases getPurchases(ResultSet resultSet) throws SQLException {
        Purchases purchases = Purchases.newBuilder()
                .id(resultSet.getLong("id"))
                .user(userDAO.readById(resultSet.getLong("user")))
                .goods(goodsDAO.readById(resultSet.getLong("goods")))
                .count(resultSet.getLong("count"))
                .timestamp(resultSet.getTimestamp("date"))
                .status(PurchasesStatus.valueOf(resultSet.getString("status")))
                .amount(resultSet.getBigDecimal("amount"))
                .build();
        return purchases;
    }

    @Override
    public List<Purchases> getPurchasesByUser(Long userId) {
        List<Purchases> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_by_user"))) {
            statement.setLong(1, userId);
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
    public List<Purchases> getPurchasesByStatus(PurchasesStatus status) {
        List<Purchases> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_by_status"))) {
            statement.setString(1, status.toString());
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
    public List<Purchases> getPurchasesByGoods(Long goodsId) {
        List<Purchases> result = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(getRequest("get_by_goods"))) {
            statement.setLong(1, goodsId);
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
