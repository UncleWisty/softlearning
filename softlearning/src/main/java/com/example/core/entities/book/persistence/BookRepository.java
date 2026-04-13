package com.example.core.entities.book.persistence;

import java.util.List;
import java.util.Optional;

import com.example.core.entities.book.dto.BookDTO;

public interface BookRepository {

    public List<BookDTO> findAll();

    public Optional<BookDTO> findByIdProduct(String id);

    public List<BookDTO> findByTitle(String title);

    public List<BookDTO> findByPartialTitle(String title);

    public Integer countByPartialTitle(String title);

    public BookDTO save(BookDTO book);

    public void deleteByIdProduct(String id);

}
