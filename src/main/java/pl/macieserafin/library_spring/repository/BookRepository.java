package pl.macieserafin.library_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.macieserafin.library_spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
