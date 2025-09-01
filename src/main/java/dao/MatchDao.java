package dao;

import model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class MatchDao implements CrudDao<Match> {
    private final SessionFactory sessionFactory;

    public MatchDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Match match) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(match);
            tx.commit();
        }
    }

    @Override
    public Optional<Match> getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from Match where id = :id", Match.class)
                    .setParameter("id", id).uniqueResultOptional();
        }
    }

    @Override
    public List<Match> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from Match", Match.class).list();
        }
    }

    public Optional <Match> getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("""
                    FROM Match m
                    WHERE m.player1.name = :name OR m.player2.name = :name
                    """, Match.class).uniqueResultOptional();
        }
    }
}
