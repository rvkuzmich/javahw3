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
        String title = "Book Title";
        int pageCount = 200;
        long authorId = 1L;
        Book bookToSave = new Book();
        bookToSave.setTitle(title);
        bookToSave.setPageCount(pageCount);
        bookToSave.setAuthor(new Author(1));
        Book book = new Book(authorId, title, pageCount);
        book.setAuthor(bookToSave.getAuthor());
        BookDto bookDto = new BookDto(1L, "Book Title", 200, 1L);

        Mockito.when(bookRepository.save(bookToSave)).thenReturn(book);
        Mockito.when(bookMapper.toDto(book)).thenReturn(bookDto);

        assertEquals(bookDto, bookService.save(title, pageCount, authorId));
    }

    @Test
    void update() {
        long bookId = 1L;
        String title = "Book Title";
        int pageCount = 200;
        long authorId = 1L;
        Book bookToUpdate = new Book(bookId, title, pageCount);
        bookToUpdate.setAuthor(new Author(authorId));
        BookDto bookDto = new BookDto(1L, "Book Title", 200, 1L);

        Mockito.when(bookRepository.save(bookToUpdate)).thenReturn(bookToUpdate);
        Mockito.when(bookMapper.toDto(bookToUpdate)).thenReturn(bookDto);

        assertEquals(bookDto, bookService.update(bookId, title, pageCount, authorId));
    }

    @Test
    void delete() {
        Long bookId = 1L;

        Mockito.doNothing().when(bookRepository).deleteById(bookId);

        bookService.delete(bookId);

        Mockito.verify(bookRepository).deleteById(bookId);
    }
}