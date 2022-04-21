package bank.demo.dto.repository;

import bank.demo.dto.domain.User;
import bank.demo.dto.repository.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


import java.util.List;
import java.util.Optional;


@Repository
@Transactional

public class CreateUser implements HibernateRepository<User> { //благодаря этому классу, можно будет его использовать в других местах
   @Autowired
    private SessionFactory sessionFactory;

    //сделав такой класс, мы потом сможем хорошо манилипулировать нашими действиями
//import org.springframework.transaction.annotation.Transactional;
    @Transactional
    @Override
    public User save(User user) {
      sessionFactory.openSession().save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.openSession().createQuery("SELECT u FROM users u").getResultList();

    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = sessionFactory.openSession().get(User.class, id);//он будет находить по ид класс, в сесиях
        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
