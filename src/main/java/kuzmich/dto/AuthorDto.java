package kuzmich.dto;

import java.util.List;
import java.util.Objects;

public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Long> bookIdList;

    public AuthorDto() {
    }

    public AuthorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDto(long id, String firstName, String lastName, List<Long> bookIdList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookIdList = bookIdList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Long> getBookIdList() {
        return bookIdList;
    }

    public void setBookIdList(List<Long> bookIdList) {
        this.bookIdList = bookIdList;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", bookIdList=" + bookIdList +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return id == authorDto.id && Objects.equals(firstName, authorDto.firstName) && Objects.equals(lastName, authorDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
