package edu.miu.apsd.apsdlmsfinalexamprep.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id) {
        super(String.format("Book with id:%d Not Found", id));
    }

}
