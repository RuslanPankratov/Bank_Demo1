package bank.demo.dto.repository;

import bank.demo.dto.domain.Credit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HibernateCredit implements HibernateRepository<Credit>{
    @Autowired
    private SessionFactory sessionFactory;//возможно проблема в том, если в одной череде пытаться заавтоврайдить один тип классов, то могут быть проблемы

    @Transactional
    @Override
    public Credit save(Credit entity) {
        sessionFactory.openSession().save(entity);
        return entity;
    }

    @Override
    public List<Credit> findAll() {
        return sessionFactory.openSession().createQuery("SELECT c FROM credits c").getResultList();
    }

    @Override
    public Optional<Credit> findById(Integer id) {
        Credit credit = sessionFactory.openSession().get(Credit.class, id);
        return Optional.ofNullable(credit);
    }

    @Override
    public void update(Credit entity) {
          sessionFactory.openSession().update(entity);
    }
}
