package edu.miu.apsd.apsdlmsfinalexamprep.service.impl;

import edu.miu.apsd.apsdlmsfinalexamprep.dto.request.BookRequestDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.AuthorResponseDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.BookResponseDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.exception.AuthorNotFoundException;
import edu.miu.apsd.apsdlmsfinalexamprep.exception.BookNotFoundException;
import edu.miu.apsd.apsdlmsfinalexamprep.model.Author;
import edu.miu.apsd.apsdlmsfinalexamprep.model.Book;
import edu.miu.apsd.apsdlmsfinalexamprep.repository.AuthorRepository;
import edu.miu.apsd.apsdlmsfinalexamprep.repository.BookRepository;
import edu.miu.apsd.apsdlmsfinalexamprep.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookResponseDTO(book.getTitle(), book.getIsbn(), mapAuthorToAuthorResponseDTO(book.getAuthors())))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book foundBook = bookRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return new BookResponseDTO(foundBook.getTitle(), foundBook.getIsbn(), mapAuthorToAuthorResponseDTO(foundBook.getAuthors()));
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Set<Long> authorIds = bookRequestDTO.authorIds() != null ? bookRequestDTO.authorIds() : Set.of();

        Book book = new Book(bookRequestDTO.title(), bookRequestDTO.isbn());
        authorRepository.findAllById(authorIds).forEach(author -> book.addAuthor(author));

        Book savedBook = bookRepository.save(book);

        return new BookResponseDTO(savedBook.getTitle(), savedBook.getIsbn(), mapAuthorToAuthorResponseDTO(savedBook.getAuthors()));
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book foundBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        foundBook.setTitle(bookRequestDTO.title());
        foundBook.setIsbn(bookRequestDTO.isbn());
        foundBook.cleanAuthors();
        authorRepository.findAllById(bookRequestDTO.authorIds()).forEach(author -> foundBook.addAuthor(author));
        Book savedBook = bookRepository.save(foundBook);

        return new BookResponseDTO(savedBook.getTitle(), savedBook.getIsbn(), mapAuthorToAuthorResponseDTO(savedBook.getAuthors()));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.deleteById(id);
    }


    private Set<AuthorResponseDTO> mapAuthorToAuthorResponseDTO(Set<Author> authors) {
        return authors.stream()
                .filter(author -> author != null)
                .map(author -> new AuthorResponseDTO(author.getName()))
                .collect(Collectors.toSet());
    }

}
