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
    public AuthorDto save(AuthorDto authorDto) {
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(authorDto)));
    }

    @Transactional
    public void update(long id, AuthorDto authorDto) {
        Author author = authorMapper.toEntity(authorDto);
        author.setId(id);
        authorRepository.save(author);
    }

    @Transactional
    public void delete(long id) {
        authorRepository.deleteById(id);
    }
}
