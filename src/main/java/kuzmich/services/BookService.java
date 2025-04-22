package kuzmich.services;

import kuzmich.dto.BookDto;
import kuzmich.mappers.BookMapper;
import kuzmich.models.Author;
import kuzmich.models.Book;
import kuzmich.repositories.AuthorRepository;
import kuzmich.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    public Optional<BookDto> findOne(long id) {
        return bookRepository.findById(id).map(bookMapper::toDto);
    }

    @Transactional
    public BookDto save(String title, int pageCount, Long authorId) {
        Author author = new Author(authorId);
        Book book = new Book();
        book.setTitle(title);
        book.setPageCount(pageCount);
        book.setAuthor(author);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    public BookDto update(Long bookId, String title, int pageCount, Long authorId) {
        Book book = new Book();
        book.setId(bookId);
        book.setTitle(title);
        book.setPageCount(pageCount);
        book.setAuthor(new Author(authorId));
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    public void delete(long id) {
        bookRepository.deleteById(id);
    }
}
