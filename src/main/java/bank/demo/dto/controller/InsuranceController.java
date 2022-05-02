package bank.demo.dto.controller;

import bank.demo.dto.domain.Insurance;
import bank.demo.dto.repository.HibernateInsurance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class InsuranceController {

    private final HibernateInsurance hibernateInsurance;


    @GetMapping("/findInsurance")
    public List<Insurance> findAllUsers() {
        return hibernateInsurance.findAll();
    }

    @GetMapping("/findInsurance/{id}")
    public Optional<Insurance> findById(@PathVariable("id") Integer id) {
        return hibernateInsurance.findById(id);
    }


    @PostMapping("/createInsurance")
    public Insurance create(@RequestBody Insurance insurance){ //нельзя использовать напрямую юзера, иначе можно записать
        // ошибочные данные, но если мы запишем ошибочные, юзер просто не будет созда

        hibernateInsurance.save(insurance);
        return insurance;
    }



    @PostMapping("/updateInsurance")
    public Insurance update(@RequestBody Insurance insurance){
        hibernateInsurance.update(insurance);
        return insurance;
    }

}
