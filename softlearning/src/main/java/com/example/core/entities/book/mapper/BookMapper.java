package com.example.core.entities.book.mapper;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.model.Book;
import com.example.shared.exceptions.BuildException;

public class BookMapper {

    public static BookDTO BookToDTO(Book b) {
        return new BookDTO(
                b.getIdProduct(),
                b.getName(),
                b.getDescription(),
                b.getPrice(),
                b.getStock(),
                b.isAvailable(),
                b.getIsbn(),
                b.getTitle(),
                b.getAuthor(),
                b.getPublisher(),
                b.getPublishYear(),
                b.getPhysicalData().getWeight(),
                b.getPhysicalData().getHeight(),
                b.getPhysicalData().getWidth(),
                b.getPhysicalData().getDepth()
        );
    }

    public static Book DTOtoBook(BookDTO dto) throws BuildException {
        return Book.getInstance(
                dto.getIdProduct(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock(),
                dto.isAvailable(),
                dto.getIsbn(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPublisher(),
                dto.getPublishYear(),
                dto.getWeight(),
                dto.getHeight(),
                dto.getWidth(),
                dto.getDepth()
        );
    }

}
