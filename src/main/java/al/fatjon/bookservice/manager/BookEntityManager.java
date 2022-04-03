package al.fatjon.bookservice.manager;

import al.fatjon.bookservice.model.BookEntity;
import al.fatjon.bookservice.repository.BookLocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookEntityManager {
    @Autowired
    BookLocalRepository localRepository;
    public BookEntity findById (Integer id){
        BookEntity bookEntity = localRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Books found with that ID"));
        return bookEntity;
    }

    public List<BookEntity> findAll(){ return localRepository.findAll();}

    public BookEntity create(BookEntity bookEntity){ return localRepository.save(bookEntity);}
}
