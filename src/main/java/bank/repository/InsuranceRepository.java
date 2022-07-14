package bank.repository;

import bank.domain.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Integer> {

}
