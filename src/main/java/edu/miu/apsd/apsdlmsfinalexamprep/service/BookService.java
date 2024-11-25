package edu.miu.apsd.apsdlmsfinalexamprep.service;

import edu.miu.apsd.apsdlmsfinalexamprep.dto.request.BookRequestDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {

    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Long id);
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);

}
