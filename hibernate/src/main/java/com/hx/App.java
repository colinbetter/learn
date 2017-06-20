package com.hx;

import com.hx.domain.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Configuration cfg = new Configuration().configure();
        System.out.println(cfg.getProperties());
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            System.out.println(session.isOpen());
            System.out.println(session.isConnected());
            Transaction tx = session.beginTransaction();
            Connection connection = session.disconnect();
            System.out.println(connection.isClosed());
            session.createNativeQuery("select 1 ").list();
            System.out.println(session.isOpen());
            System.out.println(session.isConnected());

            People p = new People();
            p.setName("huangxing");
            session.save(p);
            tx.commit();
            //开启事务
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }
}
