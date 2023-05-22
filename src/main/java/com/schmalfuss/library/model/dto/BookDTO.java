package com.schmalfuss.library.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookDTO {
    private Long id;

    private String name;

    private String resume;

    private String summary;

    private Double price;

    private Integer pages;

    private String isbn;

    private String datePublication;
}
