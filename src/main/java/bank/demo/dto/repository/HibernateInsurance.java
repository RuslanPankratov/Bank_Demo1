package bank.demo.dto.repository;

import bank.demo.dto.domain.Insurance;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class HibernateInsurance implements HibernateRepository<Insurance> {

    private SessionFactory sessionFactory;

    @Override
    public Insurance save(Insurance entity) {
        sessionFactory.openSession().save(entity);
        return entity;
    }

    @Override
    public List<Insurance> findAll() {
        return sessionFactory.openSession().createQuery("SELECT i FROM insurance i").getResultList();
    }

    @Override
    public Optional<Insurance> findById(Integer id) {
        Insurance insurance = sessionFactory.openSession().get(Insurance.class, id);
        return Optional.ofNullable(insurance);
    }

    @Override
    public void update(Insurance entity) {
        sessionFactory.openSession().update(entity);
    }
}
