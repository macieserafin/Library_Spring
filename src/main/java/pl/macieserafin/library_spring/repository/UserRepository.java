package pl.macieserafin.library_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.macieserafin.library_spring.model.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
