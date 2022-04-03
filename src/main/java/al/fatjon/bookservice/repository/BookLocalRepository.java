package al.fatjon.bookservice.repository;

import al.fatjon.bookservice.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookLocalRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findById (Integer id);
}
