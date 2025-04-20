package kuzmich.dto;

import java.util.Objects;

public class BookDto {
    private Long id;
    private String title;
    private Integer pageCount;
    private Long authorId;

    public BookDto() {
    }

    public BookDto(String title, Integer pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    public BookDto(Long id, String title, Integer pageCount, Long authorId) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Long getAuthor() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BookDto{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", pageCount=" + pageCount +
               ", authorId=" + authorId +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(title, bookDto.title) && Objects.equals(pageCount, bookDto.pageCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, pageCount);
    }
}
