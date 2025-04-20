package kuzmich.services;

import kuzmich.dto.BookDto;
import kuzmich.mappers.BookMapper;
import kuzmich.models.Author;
import kuzmich.models.Book;
import kuzmich.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private final BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private final BookMapper bookMapper = Mockito.mock(BookMapper.class);
    private final BookService bookService = new BookService(bookRepository, bookMapper);

    @Test
    void findAll() {
        Book book = new Book(1L, "Book Title", 200);
        book.setAuthor(new Author(1L));
        BookDto bookDto = new BookDto(1L, "Book Title", 200, 1L);
        List<BookDto> bookDtoList = List.of(bookDto);

        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book));
        Mockito.when(bookMapper.toDto(book)).thenReturn(bookDto);

        assertEquals(bookDtoList, bookService.findAll());
    }

    @Test
    void findOne() {
        Book book = new Book(1L, "Book Title", 200);
        book.setAuthor(new Author(1L));
        BookDto bookDto = new BookDto(1L, "Book Title", 200, 1L);

        Mockito.when(bookMapper.toDto(book)).thenReturn(bookDto);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertEquals(Optional.of(bookDto), bookService.findOne(1L));
    }

    @Test
    void save() {
        Book book = new Book(1L, "Book Title", 200);
        book.setAuthor(new Author(1L));
        BookDto bookDto = new BookDto(1L, "Book Title", 200, 1L);

        Mockito.when(bookMapper.toDto(book)).thenReturn(bookDto);
        Mockito.when(bookMapper.toEntity(bookDto)).thenReturn(book);
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        assertEquals(bookDto, bookService.save(bookDto));
    }

    @Test
    void delete() {
        Long bookId = 1L;

        Mockito.doNothing().when(bookRepository).deleteById(bookId);

        bookService.delete(bookId);

        Mockito.verify(bookRepository).deleteById(bookId);
    }
}