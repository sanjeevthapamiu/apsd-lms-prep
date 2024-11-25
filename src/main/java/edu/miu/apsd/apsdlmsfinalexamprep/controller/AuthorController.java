package edu.miu.apsd.apsdlmsfinalexamprep.controller;

import edu.miu.apsd.apsdlmsfinalexamprep.dto.request.AuthorRequestDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.AuthorResponseDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authorResponseDTOList = authorService.getAllAuthors();
        return ResponseEntity.ok(authorResponseDTOList);
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO authorResponseDTO = authorService.createAuthor(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable("id") Long id) {
        AuthorResponseDTO authorResponseDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO authorResponseDTO = authorService.updateAuthor(id, authorRequestDTO);
        return ResponseEntity.ok(authorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
