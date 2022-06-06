package bank.repository;

import bank.domain.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Integer> {

    Optional<CreditCardEntity> findByIdUser(Integer id);

}
