package bank.demo.dto.repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface HibernateRepository<T> {

    T save(T entity);

    List<T> findAll();

    Optional<T> findById(Integer id);

    void update(T entity);
}
