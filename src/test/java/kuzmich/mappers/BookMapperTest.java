package kuzmich.mappers;

import kuzmich.dto.BookDto;
import kuzmich.models.Author;
import kuzmich.models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    BookMapper mapper = new BookMapperImpl();

    @Test
    void toDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setPageCount(100);
        book.setAuthor(new Author(1L));

        BookDto expected = new BookDto();
        expected.setId(1L);
        expected.setTitle("Book Title");
        expected.setPageCount(100);
        expected.setAuthorId(1L);

        BookDto actual = mapper.toDto(book);

        assertEquals(expected, actual);

    }

    @Test
    void toEntity() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Book Title");
        bookDto.setPageCount(100);
        bookDto.setAuthorId(1L);

        Book expected = new Book();
        expected.setId(1L);
        expected.setTitle("Book Title");
        expected.setPageCount(100);
        expected.setAuthor(new Author(1L));

        Book actual = mapper.toEntity(bookDto);
        assertEquals(expected, actual);
    }

    @Test
    void mapAuthorsToAuthorIds() {
        Author author = new Author(1L);
        long actual = mapper.mapAuthorsToAuthorIds(author);

        assertEquals(author.getId(), actual);
    }

    @Test
    void mapAuthorIdsToAuthors() {
        long authorId = 1L;
        Author author = mapper.mapAuthorIdsToAuthors(authorId);

        assertEquals(authorId, author.getId());
    }
}