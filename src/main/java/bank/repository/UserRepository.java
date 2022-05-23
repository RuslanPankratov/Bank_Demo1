package bank.repository;

import bank.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findFirstByFirstName(String name);

    Optional<UserEntity> findByIdUser(Integer name);
}
