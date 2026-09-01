package pl.macieserafin.library_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.macieserafin.library_spring.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
