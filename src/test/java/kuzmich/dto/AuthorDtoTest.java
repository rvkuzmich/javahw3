package kuzmich.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDtoTest {

    @Test
    void getId() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1);
        assertEquals(1, authorDto.getId());
    }

    @Test
    void setId() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(2);
        assertEquals(2, authorDto.getId());
    }

    @Test
    void getFirstName() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName("John");
        assertEquals("John", authorDto.getFirstName());
    }

    @Test
    void setFirstName() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName("Jane");
        assertEquals("Jane", authorDto.getFirstName());
    }

    @Test
    void getLastName() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setLastName("Doe");
        assertEquals("Doe", authorDto.getLastName());
    }

    @Test
    void setLastName() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setLastName("Does");
        assertEquals("Does", authorDto.getLastName());
    }

    @Test
    void getBookIdList() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setBookIdList(List.of(1L));
        assertEquals(1, authorDto.getBookIdList().size());
        assertEquals(1L, authorDto.getBookIdList().get(0));
    }

    @Test
    void setBookIdList() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setBookIdList(List.of(1L, 2L, 5L, 9L));
        assertEquals(4, authorDto.getBookIdList().size());
        assertEquals(9L, authorDto.getBookIdList().get(3));
    }

    @Test
    void testToString() {
        AuthorDto authorDto = new AuthorDto(1L, "John", "Doe", List.of(1L, 2L, 5L, 9L));
        String expected = "AuthorDto{" +
                          "id=" + authorDto.getId() +
                          ", firstName='" + authorDto.getFirstName() + '\'' +
                          ", lastName='" + authorDto.getLastName() + '\'' +
                          ", bookIdList=" + authorDto.getBookIdList() +
                          '}';

        assertEquals(expected, authorDto.toString());
    }

    @Test
    void testEquals() {
        AuthorDto first = new AuthorDto(1L, "John", "Doe", List.of(1L));
        AuthorDto second = new AuthorDto(1L, "John", "Doe", List.of(1L));
        AuthorDto third = new AuthorDto(2L, "Jane", "Doe", List.of(1L, 2L));

        assertEquals(first, second);
        assertNotEquals(first, third);
    }

    @Test
    void testHashCode() {
        AuthorDto first = new AuthorDto(1L, "John", "Doe", List.of(1L));
        AuthorDto second = new AuthorDto(1L, "John", "Doe", List.of(1L));
        AuthorDto third = new AuthorDto(2L, "Jane", "Doe", List.of(1L, 2L));

        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first.hashCode(), third.hashCode());
    }
}