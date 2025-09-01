package dao;

import java.util.List;
import java.util.Optional;

interface CrudDao<T> {
    void save(T t);

    Optional<T> getById(int id);

    List<T> getAll();
}
