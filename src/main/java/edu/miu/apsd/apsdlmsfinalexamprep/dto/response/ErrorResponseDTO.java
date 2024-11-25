package edu.miu.apsd.apsdlmsfinalexamprep.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(
        boolean error,
        int statusCode,
        Object message
) {

    public ErrorResponseDTO(int statusCode, Object message) {
        this(true, statusCode, message);
    }

    public static ErrorResponseDTO notFound(Object message) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), message);
    }

}
