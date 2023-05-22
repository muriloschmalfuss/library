package com.schmalfuss.library.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "BOOKS")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome deve conter algum valor")
    private String name;

    @NotBlank(message = "Resumo deve conter algum valor")
    @Size(max = 500)
    private String resume;

    private String summary;

    @Min(value = 20)
    private Double price;

    @Min(value = 100)
    private Integer pages;

    @NotBlank(message = "Nome deve conter algum valor")
    private String isbn;

    private String datePublication;
}
