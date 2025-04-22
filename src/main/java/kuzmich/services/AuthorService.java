package kuzmich.services;

import kuzmich.dto.AuthorDto;
import kuzmich.mappers.AuthorMapper;
import kuzmich.models.Author;
import kuzmich.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).toList();
    }

    public Optional<AuthorDto> findOne(long id) {
        return authorRepository.findById(id).map(authorMapper::toDto);
    }

    @Transactional
    public AuthorDto save(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Transactional
    public AuthorDto update(long authorId,String newFirstName, String newLastName) {
        Author author = new Author();
        author.setId(authorId);
        author.setFirstName(newFirstName);
        author.setLastName(newLastName);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Transactional
    public void delete(long id) {
        authorRepository.deleteById(id);
    }
}
