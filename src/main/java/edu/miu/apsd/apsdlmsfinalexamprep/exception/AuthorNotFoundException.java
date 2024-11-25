package edu.miu.apsd.apsdlmsfinalexamprep.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
        super(String.format("Author with id:%d Not Found", id));
    }

}
