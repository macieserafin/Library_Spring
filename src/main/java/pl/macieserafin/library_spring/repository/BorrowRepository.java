package pl.macieserafin.library_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.macieserafin.library_spring.model.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
