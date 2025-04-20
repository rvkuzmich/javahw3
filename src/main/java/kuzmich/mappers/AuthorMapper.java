package kuzmich.mappers;

import kuzmich.dto.AuthorDto;
import kuzmich.models.Author;
import kuzmich.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "books", target = "bookIdList", qualifiedByName = "booksToBookIdList")
    AuthorDto toDto(Author author);

    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorDto authorDto);


    @Named("booksToBookIdList")
    default List<Long> booksToIdList(List<Book> books) {
        if (books == null) return Collections.emptyList();
        return books.stream()
                .map(Book::getId)
                .toList();
    }

    @Named("bookIdListToBooks")
    default List<Book> bookIdListToBooks(List<Long> bookIdList) {
        if (bookIdList == null) return Collections.emptyList();
        return bookIdList.stream()
                .map(Book::new)
                .toList();
    }
}
