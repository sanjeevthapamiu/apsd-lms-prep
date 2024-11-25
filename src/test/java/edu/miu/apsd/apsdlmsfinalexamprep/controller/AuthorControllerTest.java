package edu.miu.apsd.apsdlmsfinalexamprep.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.request.AuthorRequestDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.dto.response.AuthorResponseDTO;
import edu.miu.apsd.apsdlmsfinalexamprep.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllAuthors() throws Exception {
        List<AuthorResponseDTO> authorResponseDTOList = List.of(
                new AuthorResponseDTO("john"),
                new AuthorResponseDTO("jane")
        );

        Mockito.when(authorService.getAllAuthors()).thenReturn(authorResponseDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/authors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(authorResponseDTOList)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void createAuthor() throws Exception {
        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("john", Set.of(1L,2L,3L));
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO("john");

        Mockito.when(authorService.createAuthor(authorRequestDTO)).thenReturn(authorResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorRequestDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(authorResponseDTO)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAuthorById() throws Exception {
        Long id = 1L;
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO("john");

        Mockito.when(authorService.getAuthorById(id)).thenReturn(authorResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/authors/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(authorResponseDTO)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateAuthor() throws Exception {
        Long id = 1L;
        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("john", Set.of(1L));
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO("john");

        Mockito.when(authorService.updateAuthor(id, authorRequestDTO)).thenReturn(authorResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorRequestDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(authorResponseDTO)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteAuthor() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(authorService).deleteAuthor(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/authors/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}