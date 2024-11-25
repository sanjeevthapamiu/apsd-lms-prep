package edu.miu.apsd.apsdlmsfinalexamprep.dto.request;

import java.util.Set;

public record AuthorRequestDTO (
   String name,
   Set<Long> bookIds
) {}
