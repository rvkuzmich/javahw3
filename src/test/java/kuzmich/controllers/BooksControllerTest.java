package kuzmich.controllers;

import kuzmich.dto.BookDto;
import kuzmich.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BooksControllerTest {
    BookService bookService = Mockito.mock(BookService.class);
    BooksController booksController = new BooksController(bookService);

    @Test
    void findAll() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Book 1");
        bookDto.setPageCount(100);
        List<BookDto> bookDtoList = List.of(bookDto);
        Mockito.when(bookService.findAll()).thenReturn(bookDtoList);

        ResponseEntity<List<BookDto>> expected = new ResponseEntity<>(bookDtoList, HttpStatus.OK);

        assertEquals(expected, booksController.findAll());
    }

    @Test
    void findOne() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Book 1");
        bookDto.setPageCount(100);

        Mockito.when(bookService.findOne(1L)).thenReturn(Optional.of(bookDto));
        ResponseEntity<BookDto> expected = new ResponseEntity<>(bookDto, HttpStatus.OK);

        assertEquals(expected, booksController.findOne(1L));
    }

    @Test
    void findOneNotFound() {

        Mockito.when(bookService.findOne(1L)).thenReturn(Optional.empty());
        ResponseEntity<BookDto> expected = ResponseEntity.notFound().build();

        assertEquals(expected, booksController.findOne(1L));
    }

    @Test
    void save() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Book 1");
        bookDto.setPageCount(100);
        bookDto.setAuthorId(1L);

        Mockito.when(bookService.save(bookDto.getTitle(), bookDto.getPageCount(), bookDto.getAuthorId())).thenReturn(bookDto);
        ResponseEntity<BookDto> response = booksController.save("Book 1", 100, 1L);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Book 1");
        bookDto.setPageCount(100);
        bookDto.setAuthorId(1L);

        Mockito.when(bookService.update(bookDto.getId(),
                bookDto.getTitle(), bookDto.getPageCount(), bookDto.getAuthorId())).thenReturn(bookDto);
        ResponseEntity<BookDto> response = booksController.update(1L, "Book 1", 100, 1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void delete() {
        long bookId = 1L;

        Mockito.doNothing().when(bookService).delete(bookId);
        ResponseEntity<Void> response = booksController.delete(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}