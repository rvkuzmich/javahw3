package kuzmich.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void getId() {
        Author author = new Author();
        author.setId(1);
        assertEquals(1, author.getId());
    }

    @Test
    void setId() {
        Author author = new Author();
        author.setId(2);
        assertEquals(2, author.getId());
    }

    @Test
    void getFirstName() {
        Author author = new Author();
        author.setFirstName("John");
        assertEquals("John", author.getFirstName());
    }

    @Test
    void setFirstName() {
        Author author = new Author();
        author.setFirstName("Jane");
        assertEquals("Jane", author.getFirstName());
    }

    @Test
    void getLastName() {
        Author author = new Author();
        author.setLastName("Doe");
        assertEquals("Doe", author.getLastName());
    }

    @Test
    void setLastName() {
        Author author = new Author();
        author.setLastName("Does");
        assertEquals("Does", author.getLastName());
    }

    @Test
    void getBooks() {
        Author author = new Author();
        author.setBooks(List.of(new Book(1L)));
        assertEquals(1, author.getBooks().size());
        assertEquals(1L, author.getBooks().get(0).getId());
    }

    @Test
    void setBooks() {
        Author author = new Author();
        author.setBooks(List.of(new Book(2L)));
        assertEquals(1, author.getBooks().size());
        assertEquals(2L, author.getBooks().getFirst().getId());
    }

    @Test
    void addBook() {
        Author author = new Author();
        author.addBook(new Book(1L));
        assertEquals(1, author.getBooks().size());
        assertEquals(1L, author.getBooks().get(0).getId());
    }

    @Test
    void testToString() {
        Author author = new Author(1, "John", "Doe");
        author.setBooks(List.of(new Book(1L)));
        String expected = "Author{" +
                          "id=" + author.getId() +
                          ", firstName='" + author.getFirstName() + '\'' +
                          ", lastName='" + author.getLastName() + '\'' +
                          '}';
        assertEquals(expected, author.toString());
    }

    @Test
    void testEquals() {
        Author first = new Author(1, "John", "Doe");
        Author second = new Author(1, "John", "Doe");
        Author third = new Author(1, "Jane", "Doe");

        assertEquals(first, second);
        assertNotEquals(first, third);
    }

    @Test
    void testHashCode() {
        Author first = new Author(1, "John", "Doe");
        Author second = new Author(1, "John", "Doe");
        Author third = new Author(1, "Jane", "Doe");

        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first.hashCode(), third.hashCode());
    }
}