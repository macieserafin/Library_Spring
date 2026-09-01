package pl.macieserafin.library_spring.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.macieserafin.library_spring.model.Book;
import pl.macieserafin.library_spring.model.Borrow;
import pl.macieserafin.library_spring.model.Library;
import pl.macieserafin.library_spring.model.User;
import pl.macieserafin.library_spring.model.UserType;
import pl.macieserafin.library_spring.repository.BookRepository;
import pl.macieserafin.library_spring.repository.BorrowRepository;
import pl.macieserafin.library_spring.repository.LibraryRepository;
import pl.macieserafin.library_spring.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;

    public DataLoader(BookRepository bookRepository, BorrowRepository borrowRepository, LibraryRepository libraryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        List<Library> libraries = libraryRepository.findAll();
        if (libraries.isEmpty()) {
            libraries = libraryRepository.saveAll(List.of(
                    Library.builder()
                            .name("Biblioteka Centralna")
                            .build(),
                    Library.builder()
                            .name("Biblioteka Techniczna")
                            .build()
            ));
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            users = userRepository.saveAll(List.of(
                    User.builder()
                            .name("Anna Kowalska")
                            .email("anna.kowalska@example.com")
                            .userType(UserType.STUDENT)
                            .build(),
                    User.builder()
                            .name("Jan Nowak")
                            .email("jan.nowak@example.com")
                            .userType(UserType.TEACHER)
                            .build(),
                    User.builder()
                            .name("Marta Zielinska")
                            .email("marta.zielinska@example.com")
                            .userType(UserType.STUDENT)
                            .build()
            ));
        }

        List<Book> books = bookRepository.findAll();
        if (books.isEmpty() && !libraries.isEmpty()) {
            books = bookRepository.saveAll(List.of(
                    Book.builder()
                            .title("Lalka")
                            .author("Boleslaw Prus")
                            .year(1890)
                            .library(libraries.get(0))
                            .build(),
                    Book.builder()
                            .title("Solaris")
                            .author("Stanislaw Lem")
                            .year(1961)
                            .library(libraries.get(0))
                            .build(),
                    Book.builder()
                            .title("Clean Code")
                            .author("Robert C. Martin")
                            .year(2008)
                            .library(libraries.size() > 1 ? libraries.get(1) : libraries.get(0))
                            .build(),
                    Book.builder()
                            .title("Effective Java")
                            .author("Joshua Bloch")
                            .year(2018)
                            .library(libraries.size() > 1 ? libraries.get(1) : libraries.get(0))
                            .build()
            ));
        }

        if (borrowRepository.count() == 0 && users.size() >= 2 && books.size() >= 3) {
            borrowRepository.saveAll(List.of(
                    Borrow.builder()
                            .user(users.get(0))
                            .book(books.get(0))
                            .borrowDate(LocalDate.now().minusDays(14))
                            .returnDate(LocalDate.now().plusDays(7))
                            .build(),
                    Borrow.builder()
                            .user(users.get(1))
                            .book(books.get(2))
                            .borrowDate(LocalDate.now().minusDays(30))
                            .returnDate(LocalDate.now().minusDays(3))
                            .build()
            ));
        }
    }
}
