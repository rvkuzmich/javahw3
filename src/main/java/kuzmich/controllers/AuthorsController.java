package kuzmich.controllers;

import kuzmich.dto.AuthorDto;
import kuzmich.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorService authorService;


    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> findAll() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> findOne(@PathVariable("authorId") Long authorId) {
        return authorService.findOne(authorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorDto> save(@RequestParam("first_name") String firstName,
                                          @RequestParam("last_name") String lastName) {
        AuthorDto authorDto = authorService.save(firstName, lastName);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDto> update(@PathVariable("authorId") Long authorId,
                                       @RequestParam("first_name") String firstName,
                                       @RequestParam("last_name") String lastName) {
        AuthorDto authorDto = authorService.update(authorId, firstName, lastName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authorDto);
    }


    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> delete(@PathVariable("authorId") long authorId) {
        authorService.delete(authorId);
        return ResponseEntity.noContent().build();
    }
}
