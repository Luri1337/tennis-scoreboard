package dao;

import model.entity.FinishedMatch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class MatchDao implements CrudDao<FinishedMatch> {
    private final SessionFactory sessionFactory;

    public MatchDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(FinishedMatch match) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(match);
            tx.commit();
        }
    }

    @Override
    public Optional<FinishedMatch> getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from FinishedMatch where id = :id", FinishedMatch.class)
                    .setParameter("id", id).uniqueResultOptional();
        }
    }

    @Override
    public List<FinishedMatch> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from FinishedMatch", FinishedMatch.class).list();
        }
    }

    public List <FinishedMatch> getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("""
                    FROM FinishedMatch m
                    WHERE m.player1.name = :name OR m.player2.name = :name
                    """, FinishedMatch.class).setParameter("name",name).list();
        }
    }

    public List<FinishedMatch> getByPage(int page, int size) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("""
               FROM FinishedMatch m order by m.id
            """, FinishedMatch.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(page * size)
                    .list();
        }
    }

}
