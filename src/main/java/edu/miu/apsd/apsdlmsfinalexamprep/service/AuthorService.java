package edu.miu.apsd.apsdlmsfinalexamprep.service;

import edu.miu.apsd.apsdlmsfinalexamprep.dto.request.AuthorRequestDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteAuthor(Long id);


}
