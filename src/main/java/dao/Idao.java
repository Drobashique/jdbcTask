package dao;

import java.sql.SQLException;
import java.util.List;

public interface Idao<T> {
    T get(int id);

    List<T> getAll();

    void save (T entity);

    void update (T entity);

    void delete (T entity);

}
