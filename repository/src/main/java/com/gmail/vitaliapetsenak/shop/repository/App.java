package com.gmail.vitaliapetsenak.shop.repository;

import com.gmail.vitaliapetsenak.shop.repository.dao.GoodsDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.NewsDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.PurchasesDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.UserDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GenericDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Goods;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.repository.model.Purchases;
import com.gmail.vitaliapetsenak.shop.repository.model.User;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        GenericDAO<Goods> goodsDAO = GoodsDAOImpl.getInstance();
        GenericDAO<News> newsDAO = NewsDAOImpl.getInstance();
        GenericDAO<Purchases> purchasesDAO = PurchasesDAOImpl.getInstance();
        GenericDAO<User> userDAO = UserDAOImpl.getInstance();
        System.out.println(goodsDAO.readAll());
        System.out.println(newsDAO.readAll());
        System.out.println(purchasesDAO.readAll());
        System.out.println(userDAO.readAll());
        System.out.println(userDAO.readById(1L));
        User user = User.newBuilder().build();
        System.out.println(user);
    }
}
