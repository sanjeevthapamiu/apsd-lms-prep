package edu.miu.apsd.apsdlmsfinalexamprep.dto.response;

import java.util.Set;

public record BookResponseDTO (
   String title,
   String isbn,
   Set<AuthorResponseDTO> authors
) {}
