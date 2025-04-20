package kuzmich.services;

import kuzmich.dto.AuthorDto;
import kuzmich.mappers.AuthorMapper;
import kuzmich.models.Author;
import kuzmich.models.Book;
import kuzmich.repositories.AuthorRepository;
import kuzmich.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {


    @Test
    void findAll() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorMapper authorMapper = Mockito.mock(AuthorMapper.class);

        AuthorService authorService = new AuthorService(authorRepository, authorMapper);

        Author author = new Author(1, "John", "Doe");
        author.setBooks(List.of(new Book(1)));
        AuthorDto authorDto = new AuthorDto(1,"John", "Doe", List.of(Long.parseLong("1")));
        List<AuthorDto> authorDtoList = List.of(authorDto);

        Mockito.when(authorRepository.findAll()).thenReturn(List.of(author));
        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDtoList.getFirst());

        assertEquals(authorDtoList, authorService.findAll());
    }

    @Test
    void findOne() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorMapper authorMapper = Mockito.mock(AuthorMapper.class);

        AuthorService authorService = new AuthorService(authorRepository, authorMapper);

        Author author = new Author(1, "John", "Doe");
        author.setBooks(List.of(new Book(1)));
        AuthorDto authorDto = new AuthorDto(1,"John", "Doe", List.of(Long.parseLong("1")));

        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDto);
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        assertEquals(Optional.of(authorDto), authorService.findOne(1L));
    }

    @Test
    void save() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorMapper authorMapper = Mockito.mock(AuthorMapper.class);

        AuthorService authorService = new AuthorService(authorRepository, authorMapper);

        Author author = new Author(1, "John", "Doe");
        author.setBooks(List.of(new Book(1)));
        AuthorDto authorDto = new AuthorDto(1,"John", "Doe", List.of(Long.parseLong("1")));

        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDto);
        Mockito.when(authorMapper.toEntity(authorDto)).thenReturn(author);
        Mockito.when(authorRepository.save(author)).thenReturn(author);

        assertEquals(authorDto, authorService.save(authorDto));
    }

    @Test
    void update() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorMapper authorMapper = Mockito.mock(AuthorMapper.class);

        AuthorService authorService = new AuthorService(authorRepository, authorMapper);

        AuthorDto updatedAuthorDto = new AuthorDto(1,"Jane", "Doe", List.of(Long.parseLong("1")));

        Author authorToUpdate = new Author(1, "Jane", "Doe");
        authorToUpdate.setBooks(List.of(new Book(1)));
        Author updatedAuthor = new Author(1, "Jane", "Doe");
        updatedAuthor.setBooks(List.of(new Book(1)));

        Mockito.when(authorMapper.toEntity(updatedAuthorDto)).thenReturn(authorToUpdate);
        Mockito.when(authorMapper.toDto(authorToUpdate)).thenReturn(updatedAuthorDto);
        Mockito.when(authorRepository.save(updatedAuthor)).thenReturn(updatedAuthor);

        authorService.save(updatedAuthorDto);

        Mockito.verify(authorMapper).toEntity(updatedAuthorDto);
        Mockito.verify(authorMapper).toDto(updatedAuthor);
        Mockito.verify(authorRepository).save(updatedAuthor);
    }

    @Test
    void delete() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorMapper authorMapper = Mockito.mock(AuthorMapper.class);

        AuthorService authorService = new AuthorService(authorRepository, authorMapper);

        Long authorId = 1L;
        Mockito.doNothing().when(authorRepository).deleteById(authorId);

        authorService.delete(authorId);

        Mockito.verify(authorRepository, Mockito.times(1)).deleteById(authorId);
    }
}