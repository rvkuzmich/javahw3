package kuzmich.mappers;

import kuzmich.dto.BookDto;
import kuzmich.models.Author;
import kuzmich.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author", target = "authorId", qualifiedByName = "mapAuthorToAuthorId")
    BookDto toDto(Book book);

    @Mapping(source = "author", target = "author", qualifiedByName = "mapAuthorIdToAuthor")
    Book toEntity(BookDto bookDto);

    @Named("mapAuthorToAuthorId")
    default Long mapAuthorsToAuthorIds(Author author) {
        if (author == null) return null;
        return author.getId();
    }

    @Named("mapAuthorIdToAuthor")
    default Author mapAuthorIdsToAuthors(Long authorId) {
        if (authorId == null) return null;
        Author author = new Author();
        author.setId(authorId);
        return author;
    }
}
