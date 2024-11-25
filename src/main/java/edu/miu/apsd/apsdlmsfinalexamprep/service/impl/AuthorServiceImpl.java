package edu.miu.apsd.apsdlmsfinalexamprep.service.impl;

import edu.miu.apsd.apsdlmsfinalexamprep.dto.request.AuthorRequestDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.AuthorResponseDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.exception.AuthorNotFoundException;
import edu.miu.apsd.apsdlmsfinalexamprep.model.Author;
import edu.miu.apsd.apsdlmsfinalexamprep.repository.AuthorRepository;
import edu.miu.apsd.apsdlmsfinalexamprep.repository.BookRepository;
import edu.miu.apsd.apsdlmsfinalexamprep.service.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorResponseDTO(author.getName()))
                .toList();
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        Author foundAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return new AuthorResponseDTO(foundAuthor.getName());
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        Set<Long> bookIds = authorRequestDTO.bookIds() != null ? authorRequestDTO.bookIds() : Set.of();

        Author author = new Author(authorRequestDTO.name());
        bookRepository.findAllById(bookIds).forEach(book -> author.addBook(book));
        Author savedAuthor = authorRepository.save(author);

        return new AuthorResponseDTO(savedAuthor.getName());
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author foundAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        foundAuthor.setName(authorRequestDTO.name());
        foundAuthor.clearBooks();
        bookRepository.findAllById(authorRequestDTO.bookIds()).forEach(book -> foundAuthor.addBook(book));
        Author savedAuthor = authorRepository.save(foundAuthor);

        return new AuthorResponseDTO(savedAuthor.getName());
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.deleteById(id);
    }
}
