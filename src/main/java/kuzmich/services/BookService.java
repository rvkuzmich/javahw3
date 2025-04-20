package kuzmich.services;

import kuzmich.dto.BookDto;
import kuzmich.mappers.BookMapper;
import kuzmich.models.Book;
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
    public BookDto save(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public BookDto update(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public void delete(long id) {
        bookRepository.deleteById(id);
    }
}
