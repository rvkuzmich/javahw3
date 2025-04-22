package kuzmich.controllers;

import kuzmich.dto.AuthorDto;
import kuzmich.models.Author;
import kuzmich.models.Book;
import kuzmich.services.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class AuthorsControllerTest {

    AuthorService authorService = Mockito.mock(AuthorService.class);
    AuthorsController authorsController = new AuthorsController(authorService);

    @Test
    void findAll() {
        AuthorDto authorDto = new AuthorDto(1L, "John", "Doe", new ArrayList<>(List.of(1L)));
        List<AuthorDto> authorDtoList = List.of(authorDto);

        Mockito.when(authorService.findAll()).thenReturn(authorDtoList);

        ResponseEntity<List<AuthorDto>> expected = new ResponseEntity<>(authorDtoList, HttpStatus.OK);

        assertEquals(expected, authorsController.findAll());
    }

    @Test
    void findOne() {
        AuthorDto authorDto = new AuthorDto(1L, "John", "Doe", new ArrayList<>(List.of(1L)));

        Mockito.when(authorService.findOne(1L)).thenReturn(Optional.of(authorDto));

        ResponseEntity<AuthorDto> expected = new ResponseEntity<>(authorDto, HttpStatus.OK);

        assertEquals(expected, authorsController.findOne(1L));
    }

    @Test
    void findOneNotFound() {

        Mockito.when(authorService.findOne(1L)).thenReturn(Optional.empty());

        ResponseEntity<AuthorDto> expected = ResponseEntity.notFound().build();

        assertEquals(expected, authorsController.findOne(1L));
    }

    @Test
    void save() {
        AuthorDto authorDto = new AuthorDto(1L, "John", "Doe", new ArrayList<>());

        Mockito.when(authorService.save(authorDto.getFirstName(), authorDto.getLastName())).thenReturn(authorDto);
        ResponseEntity<AuthorDto> response = authorsController.save("John", "Doe");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    void update() {
        AuthorDto authorDto = new AuthorDto(1L, "Jane", "Doe", new ArrayList<>());

        Mockito.when(authorService.update(authorDto.getId(), authorDto.getFirstName(), authorDto.getLastName())).thenReturn(authorDto);

        ResponseEntity<AuthorDto> response = authorsController.update(1L, "Jane", "Doe");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void delete() {
        long authorId = 1L;
        Mockito.doNothing().when(authorService).delete(1L);

        ResponseEntity<Void> response = authorsController.delete(authorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}