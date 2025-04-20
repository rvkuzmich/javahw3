package kuzmich.controllers;

import kuzmich.dto.BookDto;
import kuzmich.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findOne(@PathVariable("bookId") Long bookId) {
        return bookService.findOne(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestParam("title") String title,
                                        @RequestParam("page_count") Integer pageCount,
                                        @RequestParam("author_id") Long authorId) {
        BookDto bookDto = new BookDto();
        bookDto.setId(0L);
        bookDto.setTitle(title);
        bookDto.setPageCount(pageCount);
        bookDto.setAuthorId(authorId);
        bookDto = bookService.save(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Void> update(@PathVariable("bookId") Long bookId,
                                       @RequestParam("title") String title,
                                       @RequestParam("page_count") Integer pageCount) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setPageCount(pageCount);
        bookService.update(bookId, bookDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable("bookId") Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }
}
