package com.example.infraestructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.persistence.BookRepository;

@Repository
public interface JpaBookRepository extends JpaRepository<BookDTO, String>, BookRepository {

    @Override
    public Optional<BookDTO> findByIdProduct(String id);

    @Override
    public List<BookDTO> findByTitle(String title);

    @Override
    @Query(value = "SELECT b FROM BookDTO b WHERE b.name LIKE %:title%")
    public List<BookDTO> findByPartialTitle(String title);

    @Override
    @Query(value = "SELECT count(*) FROM BookDTO b WHERE b.name LIKE %:title%")
    public Integer countByPartialTitle(String title);

    @Override
    public BookDTO save(BookDTO book);

    @Override
    public void deleteByIdProduct(String id);
}
