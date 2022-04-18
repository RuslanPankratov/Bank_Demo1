package bank.demo.dto.bd.table;

import java.util.List;
import java.util.Optional;

public interface HibernateRepository<T> {

    T save(T entity);

    List<T> findAll();

    Optional<T> findById(Integer id);

    void update(T entity);
}
