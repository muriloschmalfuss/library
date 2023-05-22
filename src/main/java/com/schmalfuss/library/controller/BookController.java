package com.schmalfuss.library.controller;

import com.schmalfuss.library.model.dto.BookDTO;
import com.schmalfuss.library.model.dto.MessageDTO;
import com.schmalfuss.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> list() {
        try {
            return ResponseEntity.ok(bookService.getAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(bookService.getById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BookDTO bookDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(@RequestBody BookDTO bookDTO, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(bookService.edit(bookDTO, id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        try {
            bookService.destroy(id);
            return ResponseEntity.ok(new MessageDTO("Livro removido com sucesso"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO(e.getMessage()));
        }
    }
}
