package al.fatjon.bookservice.service;

import al.fatjon.bookservice.manager.BookEntityManager;
import al.fatjon.bookservice.model.Author;
import al.fatjon.bookservice.model.BookEntity;
import al.fatjon.bookservice.model.BookPojo;
import al.fatjon.bookservice.model.Category;
import al.fatjon.bookservice.model.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class BookService {
    @Autowired
    BookEntityManager bookEntityManager;
    @Autowired
    HttpService httpService;

    public BookPojo getById(Integer id) {
        return entityToPojo(bookEntityManager.findById(id));
    }

    public List<BookPojo> getAll() {
        return bookEntityManager.findAll().stream().map(e -> entityToPojo(e)).collect(Collectors.toList());
    }

    public void setNewBooks(BookPojo book) {
        BookEntity createdEntity = bookEntityManager.create(pojoToEntity(book));
        book.getCategories().forEach(c -> httpService.createCategoryRelation(createdEntity.getId(), c.getId()));
    }

    private BookEntity pojoToEntity(BookPojo book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setImage(book.getImage());
        bookEntity.setAuthorId(book.getAuthor().getId());
        return bookEntity;
    }

    private BookPojo entityToPojo(BookEntity bookEntity) {
        BookPojo book = new BookPojo();
        book.setName(bookEntity.getName());
        book.setDescription(bookEntity.getDescription());
        book.setImage(bookEntity.getImage());
        book.setAuthor(httpService.getAuthorById(bookEntity.getAuthorId()));
        List<Category> ls = httpService.getCategoriesByBook(bookEntity.getId());
        book.setCategories(ls);
        return book;
    }

}
