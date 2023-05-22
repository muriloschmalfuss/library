package com.schmalfuss.library.model.mapper;

import com.schmalfuss.library.model.dto.BookDTO;
import com.schmalfuss.library.model.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO update(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setName(bookEntity.getName());
        bookDTO.setResume(bookEntity.getResume());
        bookDTO.setSummary(bookEntity.getSummary());
        bookDTO.setPrice(bookEntity.getPrice());
        bookDTO.setPages(bookEntity.getPages());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setDatePublication(bookEntity.getDatePublication());
        return bookDTO;
    }

    public BookEntity update(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setName(bookDTO.getName());
        bookEntity.setResume(bookDTO.getResume());
        bookEntity.setSummary(bookDTO.getSummary());
        bookEntity.setPrice(bookDTO.getPrice());
        bookEntity.setPages(bookDTO.getPages());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setDatePublication(bookDTO.getDatePublication());
        return bookEntity;
    }
}
