package com.gmail.vitaliapetsenak.shop.repository;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.*;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = session.get(User.class, 1L);
        Product product = session.get(Product.class, 1L);
        Product product1 = session.get(Product.class, 14L);
        Product product2 = session.get(Product.class, 15L);
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        order.setUser(user);
        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product), 1));
        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product1), 2));
        order.addOrderProduct(new OrderProduct(new OrderProductId(order, product2), 3));
        session.save(order);
        Set<Comment> comments = user.getComments();
        for (Comment comment : comments) {
            System.out.println(comment);
        }
        Set<OrderProduct> orderProducts = product.getOrderProducts();
        for (OrderProduct orderProduct : orderProducts) {
            System.out.println(orderProduct.getOrder());
        }
        System.out.println(product);
        session.getTransaction().commit();
    }
}
