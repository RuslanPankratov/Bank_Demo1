package bank.repository;

import bank.domain.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditRepository extends JpaRepository<CreditEntity, Integer> {

    Optional<CreditEntity> findByIdUser(Integer id);
}
