package bank.demo.dto.core;

import bank.demo.dto.domain.User;
import bank.demo.dto.dto.FindAllUser;
import bank.demo.dto.dto.Users1;
import bank.demo.dto.repository.HibernateRepository;

import org.springframework.stereotype.Component;

@Component
public class FindAllUserServise {
    private  final HibernateRepository<User> repository;

    public FindAllUserServise(HibernateRepository<User> repository) {
        this.repository = repository;
    }


    public FindAllUser findAll(){
        var dtos = repository.findAll().stream()
                .map(this::convert)
                .toList();
        return new FindAllUser(dtos);

    }

    private Users1 convert(User user){
        return new Users1(user.getFirstName(), user.getLastName(), user.getAge(), user.getTypeOfBenefits(), user.getIdUser());
    }
}
