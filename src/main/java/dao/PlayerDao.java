package dao;

import model.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PlayerDao implements CrudDao<Player> {
    private final SessionFactory sessionFactory;

    public PlayerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Player player) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(player);
            tx.commit();
        }
    }

    public Optional<Player> getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from Player where name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();

        }
    }

    @Override
    public Optional<Player> getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Player where id = :id", Player.class)
                    .setParameter("id", id).uniqueResultOptional();
        }
    }

    @Override
    public List<Player> getAll() {
        return List.of();
    }

    public void deleteByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from Player where name = :name")
                    .setParameter("name", name)
                    .executeUpdate();
            tx.commit();
        }
    }
}
