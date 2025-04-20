package kuzmich.mappers;

import kuzmich.dto.AuthorDto;
import kuzmich.models.Author;
import kuzmich.models.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {
    AuthorMapper mapper = new AuthorMapperImpl();

    @Test
    void toDto() {
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setBooks(new ArrayList<>(List.of(new Book(1L))));

        AuthorDto expected = new AuthorDto();
        expected.setId(1L);
        expected.setFirstName("John");
        expected.setLastName("Doe");
        expected.setBookIdList(new ArrayList<>(List.of(1L)));

        AuthorDto actual = mapper.toDto(author);
        assertEquals(expected, actual);
    }

    @Test
    void toEntity() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setFirstName("John");
        authorDto.setLastName("Doe");
        authorDto.setBookIdList(new ArrayList<>(List.of(1L)));

        Author expected = new Author();
        expected.setId(1L);
        expected.setFirstName("John");
        expected.setLastName("Doe");
        expected.setBooks(new ArrayList<>(List.of(new Book(1L))));

        Author actual = mapper.toEntity(authorDto);
        assertEquals(expected, actual);
    }

    @Test
    void booksToIds() {
        List<Book> books = new ArrayList<>(List.of(new Book(1L), new Book(2L), new Book(3L)));

        List<Long> bookIdList = mapper.booksToIdList(books);

        assertEquals(bookIdList.get(0), books.get(0).getId());
        assertEquals(bookIdList.get(1), books.get(1).getId());
        assertEquals(bookIdList.get(2), books.get(2).getId());
    }

    @Test
    void bookIdListToBooks() {
        List<Long> bookIdList = new ArrayList<>(List.of(1L, 2L, 3L));
        List<Book> books = mapper.bookIdListToBooks(bookIdList);

        assertEquals(bookIdList.get(0), books.get(0).getId());
        assertEquals(bookIdList.get(1), books.get(1).getId());
        assertEquals(bookIdList.get(2), books.get(2).getId());
    }
}