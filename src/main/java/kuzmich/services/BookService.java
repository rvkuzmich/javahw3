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
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(AuthorRepository authorRepository, BookRepository bookRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
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
    public BookDto save(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public void update(long id, BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(long id) {
        bookRepository.deleteById(id);
    }
}
