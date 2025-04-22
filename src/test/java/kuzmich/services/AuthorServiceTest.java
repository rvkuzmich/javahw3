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

    private final AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
    private final AuthorMapper authorMapper = Mockito.mock(AuthorMapper.class);
    private final AuthorService authorService = new AuthorService(authorRepository, authorMapper);


    @Test
    void findAll() {
        Author author = new Author(1, "John", "Doe");
        author.setBooks(List.of(new Book(1L)));
        AuthorDto authorDto = new AuthorDto(1L,"John", "Doe", List.of(Long.parseLong("1")));
        List<AuthorDto> authorDtoList = List.of(authorDto);

        Mockito.when(authorRepository.findAll()).thenReturn(List.of(author));
        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDto);

        assertEquals(authorDtoList, authorService.findAll());
    }

    @Test
    void findOne() {
        Author author = new Author(1, "John", "Doe");
        author.setBooks(List.of(new Book(1L)));
        AuthorDto authorDto = new AuthorDto(1L,"John", "Doe", List.of(Long.parseLong("1")));

        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDto);
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        assertEquals(Optional.of(authorDto), authorService.findOne(1L));
    }

    @Test
    void save() {
        String firstName = "John";
        String lastName = "Doe";
        Author authorToSave = new Author();
        authorToSave.setFirstName(firstName);
        authorToSave.setLastName(lastName);
        Author author = new Author(1L, firstName, lastName, new ArrayList<>());
        AuthorDto authorDto = new AuthorDto(1L, firstName, lastName, new ArrayList<>());

        Mockito.when(authorRepository.save(authorToSave)).thenReturn(author);
        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDto);

        assertEquals(authorDto, authorService.save(author.getFirstName(), author.getLastName()));

    }

    @Test
    void update() {
        long authorId = 1L;
        String firstName = "John";
        String lastName = "Doe";
        Author author = new Author(authorId, firstName, lastName);
        author.setBooks(List.of(new Book(1L)));
        AuthorDto authorDto = new AuthorDto(1L,"John", "Doe", List.of(Long.parseLong("1")));

        Mockito.when(authorMapper.toDto(author)).thenReturn(authorDto);
        Mockito.when(authorRepository.save(author)).thenReturn(author);

        assertEquals(authorDto, authorService.update(authorId, firstName, lastName));
    }

    @Test
    void delete() {
        Long authorId = 1L;

        Mockito.doNothing().when(authorRepository).deleteById(authorId);

        authorService.delete(authorId);

        Mockito.verify(authorRepository).deleteById(authorId);
    }

}