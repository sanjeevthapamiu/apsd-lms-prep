package edu.miu.apsd.apsdlmsfinalexamprep.dto.request;

import java.util.Set;

public record BookRequestDTO (
   String title,
   String isbn,
   Set<Long> authorIds
) {}
