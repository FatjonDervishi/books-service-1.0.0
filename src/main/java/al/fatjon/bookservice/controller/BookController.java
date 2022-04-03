package al.fatjon.bookservice.controller;

import al.fatjon.bookservice.model.BookPojo;
import al.fatjon.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/library/book-service")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(bookService.getById(id));
        } catch(IllegalArgumentException ie){
            return ResponseEntity.badRequest().body(ie);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<BookPojo>> getBooks(){ return ResponseEntity.ok(bookService.getAll());}

    @PostMapping()
    public ResponseEntity<Object> createBooks (@RequestBody BookPojo books){
        bookService.setNewBooks(books);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully");
    }
}
