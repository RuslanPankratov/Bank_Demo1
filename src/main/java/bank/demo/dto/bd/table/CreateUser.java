package bank.demo.dto.bd.table;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Repository
@Data
public class CreateUser implements HibernateRepository<User> { //благодаря этому классу, можно будет его использовать в других местах
    private SessionFactory sessionFactory;

    //сделав такой класс, мы потом сможем хорошо манилипулировать нашими действиями
//import org.springframework.transaction.annotation.Transactional;
    @Transactional
    @Override
    public User save(User user) {
//        Transactional transactional = null;
//        try(Session session = sessionFactory.openSession()){
//
//            session.beginTransaction();
//
//            session.save(user);
//            transactional.commit();
//
//        }
//       Session session = sessionFactory.openSession();
//       session.get(User.class, 2);

        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT u FROM users u").getResultList();

    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);//он будет находить по ид класс, в сесиях
        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
