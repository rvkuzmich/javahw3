package kuzmich.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDtoTest {

    @Test
    void getId() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        assertEquals(1L, bookDto.getId());
    }

    @Test
    void setId() {
        BookDto bookDto = new BookDto();
        bookDto.setId(2L);
        assertEquals(2L, bookDto.getId());
    }

    @Test
    void getTitle() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Title");
        assertEquals("Title", bookDto.getTitle());
    }

    @Test
    void setTitle() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Book Title");
        assertEquals("Book Title", bookDto.getTitle());
    }

    @Test
    void getPageCount() {
        BookDto bookDto = new BookDto();
        bookDto.setPageCount(1);
        assertEquals(1, bookDto.getPageCount());
    }

    @Test
    void setPageCount() {
        BookDto bookDto = new BookDto();
        bookDto.setPageCount(200);
        assertEquals(200, bookDto.getPageCount());
    }

    @Test
    void getAuthorId() {
        BookDto bookDto = new BookDto();
        bookDto.setAuthorId(1L);
        assertEquals(1L, bookDto.getAuthorId());
    }

    @Test
    void setAuthorId() {
        BookDto bookDto = new BookDto();
        bookDto.setAuthorId(2L);
        assertEquals(2L, bookDto.getAuthorId());
    }

    @Test
    void testToString() {
        BookDto bookDto = new BookDto(1L, "Title", 200, 1L);
        String expected = "BookDto{" +
                          "id=" + bookDto.getId() +
                          ", title='" + bookDto.getTitle() + '\'' +
                          ", pageCount=" + bookDto.getPageCount() +
                          ", authorId=" + bookDto.getAuthorId() +
                          '}';

        assertEquals(expected, bookDto.toString());
    }

    @Test
    void testEquals() {
        BookDto first = new BookDto(1L, "Title", 200, 1L);
        BookDto second = new BookDto(1L, "Title", 200, 1L);
        BookDto third = new BookDto(2L, "Title2", 250, 1L);

        assertEquals(first, second);
        assertNotEquals(first, third);
    }

    @Test
    void testHashCode() {
        BookDto first = new BookDto(1L, "Title", 200, 1L);
        BookDto second = new BookDto(1L, "Title", 200, 1L);
        BookDto third = new BookDto(2L, "Title2", 250, 1L);

        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first.hashCode(), third.hashCode());
    }
}