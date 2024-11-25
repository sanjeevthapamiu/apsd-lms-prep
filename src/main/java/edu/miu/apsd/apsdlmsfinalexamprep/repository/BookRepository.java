package edu.miu.apsd.apsdlmsfinalexamprep.repository;

import edu.miu.apsd.apsdlmsfinalexamprep.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
