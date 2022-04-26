package bank.demo.dto.repository;


import bank.demo.dto.domain.CreditCard;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HibernateCreditCard implements HibernateRepository<CreditCard> {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public CreditCard save(CreditCard entity) {
        sessionFactory.openSession().save(entity);
        return entity;
    }

    @Override
    public List<CreditCard> findAll() {
        return sessionFactory.openSession().createQuery("SELECT u FROM credit_cards u").getResultList();
    }

    @Override
    public Optional<CreditCard> findById(Integer id) {
        var card = sessionFactory.openSession().get(CreditCard.class, id);//поиск по ид по сути это как поиск по ячейкам
        return Optional.ofNullable(card);
    }

    @Override
    public void update(CreditCard entity) {
        sessionFactory.openSession().update(entity);
    }
}
