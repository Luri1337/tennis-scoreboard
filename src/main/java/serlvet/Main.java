package serlvet;

import model.Player;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Player .class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.persist(new Player("TestName"));

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    }

