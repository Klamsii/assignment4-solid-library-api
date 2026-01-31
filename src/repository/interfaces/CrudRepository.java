package repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    void create(T t);

    Optional<T> findById(int id);

    List<T> findAll();

    void delete(int id);
}
