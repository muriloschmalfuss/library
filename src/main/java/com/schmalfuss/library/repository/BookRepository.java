package com.schmalfuss.library.repository;

import com.schmalfuss.library.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByNameOrIsbn(String name, String isbn);
    @Query("SELECT b FROM BookEntity b WHERE UPPER(b.name) LIKE CONCAT('%',UPPER(:name) ,'%') OR b.isbn LIKE CONCAT('%',:isbn,'%')")
    List<BookEntity> filter(@Param("name") String name, @Param("isbn") String isbn);

}
