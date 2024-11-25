package edu.miu.apsd.apsdlmsfinalexamprep.repository;

import edu.miu.apsd.apsdlmsfinalexamprep.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
