package bank.demo.dto.bd.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Repository
@Transactional
@AllArgsConstructor
@Data
public class CreateUser implements HibernateRepository<Userbank> { //благодаря этому классу, можно будет его использовать в других местах
    private SessionFactory sessionFactory;

    //сделав такой класс, мы потом сможем хорошо манилипулировать нашими действиями
//import org.springframework.transaction.annotation.Transactional;
    @Transactional
    @Override
    public Userbank save(Userbank user) {
        user.setIdUser(1);
//        System.out.println(user.toString());
//        Transaction transaction = null;
//        try(Session session = sessionFactory.openSession()){
//
//
//            session.beginTransaction();
//
//            session.save(user);
//            transaction.commit();
//        } catch (Exception e){
//            e.printStackTrace();
//        }

      sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public List<Userbank> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT u FROM users u").getResultList();

    }

    @Override
    public Optional<Userbank> findById(Integer id) {
        Userbank user = sessionFactory.getCurrentSession().get(Userbank.class, id);//он будет находить по ид класс, в сесиях
        return Optional.ofNullable(user);
    }

    @Override
    public void update(Userbank user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
