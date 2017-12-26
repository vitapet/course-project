package com.gmail.vitaliapetsenak.shop.repository;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.OrderDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.ProductDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.UserDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.*;
import org.hibernate.Hibernate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        ProductDAO productDAO = ProductDAO.getInstance();
        OrderDAO orderDAO = OrderDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        Set<Order> afterAdd;
        Set<Order> afterDelete;

        productDAO.beginTransaction();
        Product product = productDAO.findById(1L);
        productDAO.commitTransaction();

        userDAO.beginTransaction();
        User user = userDAO.findById(6L);
        userDAO.commitTransaction();

        orderDAO.beginTransaction();
        user = userDAO.findById(6L);
        Hibernate.initialize(user.getOrders());
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        order.setUser(user);
        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product), 1));
        user.getOrders().add(order);
//        orderDAO.create(order);
        orderDAO.commitTransaction();

        userDAO.beginTransaction();
        user = userDAO.findById(6L);
//        afterAdd.addAll(user.getOrders());
//        afterAdd = new HashSet<>(user.getOrders());
        Hibernate.initialize(user.getOrders());
        userDAO.commitTransaction();
        System.out.println("from user before" + user.getOrders());

        orderDAO.beginTransaction();
        orderDAO.delete(order);
        orderDAO.commitTransaction();

        userDAO.beginTransaction();
        user = userDAO.findById(6L);
//        afterDelete.addAll(user.getOrders());
//        afterDelete = new HashSet<>(user.getOrders());
        Hibernate.initialize(user.getOrders());
        userDAO.commitTransaction();
//        System.out.println("after add: " + afterAdd);
//        System.out.println("after delete: " + afterDelete);
        System.out.println("from user after" + user.getOrders());
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//        User user = session.get(User.class, 6L);
//        Product product = session.get(Product.class, 1L);
////        Product product1 = session.get(Product.class, 14L);
////        Product product2 = session.get(Product.class, 15L);
//        order = new Order();
//        order.setStatus(OrderStatus.NEW);
//        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//        order.setUser(user);
//        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product), 1));
////        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product1), 2));
////        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product2), 3));
//        session.save(order);
//        Set<Comment> comments = user.getComments();
//        for (Comment comment : comments) {
//            System.out.println(comment);
//        }
//        Set<OrderProduct> orderProducts = product.getOrderProducts();
//        for (OrderProduct orderProduct : orderProducts) {
//            System.out.println(orderProduct.getOrder());
//        }
//        System.out.println(product);
//        System.out.println(order);
////        System.out.println("before" + user.getOrders());
//        session.delete(order);
////        user.getOrders().remove(order);
////        user.getOrders();
//        session.getTransaction().commit();
////        System.out.println("before" + user.getOrders());
//        Session session1 = HibernateUtil.getSession();
//        session1.getTransaction().begin();
//        user = session1.get(User.class, 6L);
//        System.out.println(user.getOrders());
//        session1.getTransaction().commit();
////        System.out.println(order);
//        System.out.println("after delete" + user.getOrders());


    }
}
