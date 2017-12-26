package com.gmail.vitaliapetsenak.shop.repository.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
