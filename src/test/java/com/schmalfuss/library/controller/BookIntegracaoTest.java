package com.schmalfuss.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schmalfuss.library.LibraryApplication;
import com.schmalfuss.library.model.entity.BookEntity;
import com.schmalfuss.library.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void populate() {
        for (int i = 0; i < 10; i++) {
            BookEntity book = new BookEntity();
            book.setName("teste");
            book.setResume("resumo");
            book.setSummary("sumario");
            book.setPrice(20.0);
            book.setPages(100);
            book.setIsbn("123456789");
            book.setDatePublication("2023-10-10");

            bookRepository.save(book);
        }
    }

    @Test
    public void saveBook() throws Exception {
        BookEntity book = new BookEntity();
        book.setName("teste");
        book.setResume("resumo");
        book.setSummary("sumario");
        book.setPrice(20.0);
        book.setPages(100);
        book.setIsbn("123456789");
        book.setDatePublication("2023-10-10");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        BookEntity bookEntity =bookRepository.findById(1L).get();
        Assertions.assertEquals("teste", bookEntity.getName());
    }

    @Test
    public void notSaveBook() throws Exception {
        BookEntity book = new BookEntity();
        book.setName("teste");
        book.setResume("a".repeat(501));
        book.setSummary("sumario");
        book.setPrice(20.0);
        book.setPages(100);
        book.setIsbn("123456789");
        book.setDatePublication("2023-10-10");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateBook() throws Exception {
        BookEntity book = new BookEntity();
        book.setName("teste-update");
        book.setResume("resumo");
        book.setSummary("sumario");
        book.setPrice(20.0);
        book.setPages(100);
        book.setIsbn("123456789");
        book.setDatePublication("2023-10-10");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/books/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        BookEntity bookEntity =bookRepository.findById(3L).get();
        Assertions.assertEquals("teste-update", bookEntity.getName());
    }

    @Test
    public void notUpdateBook() throws Exception {
        BookEntity book = new BookEntity();
        book.setName("teste-update");
        book.setResume("resumo");
        book.setSummary("sumario");
        book.setPrice(20.0);
        book.setPages(100);
        book.setIsbn("123456789");
        book.setDatePublication("2023-10-10");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/books/300")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getBooks() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getBooksByIdFound() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getBooksByIdNotFound() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/books/200")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteBooksByIdFound() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteBooksByIdNotFound() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/books/200")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
