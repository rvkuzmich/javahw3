package kuzmich.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getId() {
        Book book = new Book();
        book.setId(1);
        assertEquals(1, book.getId());
    }

    @Test
    void setId() {
        Book book = new Book();
        book.setId(2);
        assertEquals(2, book.getId());
    }

    @Test
    void getTitle() {
        Book book = new Book();
        book.setTitle("Title");
        assertEquals("Title", book.getTitle());
    }

    @Test
    void setTitle() {
        Book book = new Book();
        book.setTitle("Book Title");
        assertEquals("Book Title", book.getTitle());
    }

    @Test
    void getPageCount() {
        Book book = new Book();
        book.setPageCount(1);
        assertEquals(1, book.getPageCount());
    }

    @Test
    void setPageCount() {
        Book book = new Book();
        book.setPageCount(200);
        assertEquals(200, book.getPageCount());
    }

    @Test
    void getAuthor() {
        Book book = new Book();
        book.setAuthor(new Author(1L));
        assertEquals(1L, book.getAuthor().getId());
    }

    @Test
    void setAuthor() {
        Book book = new Book();
        book.setAuthor(new Author(2L));
        assertEquals(2L, book.getAuthor().getId());
    }

    @Test
    void testToString() {
        Book book = new Book(1L, "Book Title", 200);
        book.setAuthor(new Author(1L));
        String expected = "Book{" +
                          "id=" + book.getId() +
                          ", title='" + book.getTitle() + '\'' +
                          ", pageCount=" + book.getPageCount() +
                          '}';

        assertEquals(expected, book.toString());
    }

    @Test
    void testEquals() {
        Book first = new Book(1L, "Book Title", 200);
        Book second = new Book(1L, "Book Title", 200);
        Book third = new Book(2L, "Book Title", 200);

        assertEquals(first, second);
        assertNotEquals(first, third);
    }

    @Test
    void testHashCode() {
        Book first = new Book(1L, "Book Title", 200);
        Book second = new Book(1L, "Book Title", 200);
        Book third = new Book(2L, "Book Title", 200);

        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first.hashCode(), third.hashCode());
    }
}