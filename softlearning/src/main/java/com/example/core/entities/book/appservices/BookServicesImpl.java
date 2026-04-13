package com.example.core.entities.book.appservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.core.entities.book.dto.BookDTO;
import com.example.core.entities.book.mapper.BookMapper;
import com.example.core.entities.book.persistence.BookRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.Serializers;
import com.example.services.serializers.SerializersCatalog;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.ServiceException;

@Controller
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookRepository bookRepository;
    private Serializer<BookDTO> serializer;

    // ****** Implementing the business logic methods and common featues (clean code design) ******
    protected BookDTO getDTO(String id) {
        return bookRepository.findByIdProduct(id).orElse(null);
    }

    protected BookDTO getByIdProduct(String id) throws ServiceException {
        BookDTO bdto = this.getDTO(id);

        if (bdto == null) {
            throw new ServiceException("book " + id + " not found");
        }
        return bdto;
    }

    protected BookDTO checkInputData(String book) throws ServiceException {
        try {
            BookDTO bdto = (BookDTO) this.serializer.deserialize(book, BookDTO.class);
            BookMapper.DTOtoBook(bdto);
            return bdto;
        } catch (BuildException e) {
            throw new ServiceException("error in the input book data: " + e.getMessage());
        }
    }

    @Override
    public String getAllBooksToJson() throws ServiceException {
        Serializer<List<BookDTO>> listSerializer = (Serializer<List<BookDTO>>) SerializersCatalog.getInstance(Serializers.JSON_BOOK);
        return listSerializer.serialize(bookRepository.findAll());
    }

    protected BookDTO getById(String id) throws ServiceException {
        return this.getByIdProduct(id);
    }

    protected BookDTO newBook(String book) throws ServiceException {
        BookDTO bdto = this.checkInputData(book);

        if (this.getDTO(bdto.getIdProduct()) == null) {
            return bookRepository.save(bdto);
        }
        throw new ServiceException("book " + bdto.getIdProduct() + " already exists");
    }

    protected BookDTO updateBook(String book) throws ServiceException {
        BookDTO bdto = this.checkInputData(book);
        this.getByIdProduct(bdto.getIdProduct());
        return bookRepository.save(bdto);
    }

    // ****** Implementing the interface methods ******
    @Override
    public String getByIdProductToJson(String id) throws ServiceException {
        this.serializer = (Serializer<BookDTO>) SerializersCatalog.getInstance(Serializers.JSON_BOOK);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String getByIdProductToXml(String id) throws ServiceException {
        this.serializer = (Serializer<BookDTO>) SerializersCatalog.getInstance(Serializers.XML_BOOK);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String addFromJson(String book) throws ServiceException {
        this.serializer = (Serializer<BookDTO>) SerializersCatalog.getInstance(Serializers.JSON_BOOK);
        return serializer.serialize(this.newBook(book));
    }

    @Override
    public String addFromXml(String book) throws ServiceException {
        this.serializer = (Serializer<BookDTO>) SerializersCatalog.getInstance(Serializers.XML_BOOK);
        return serializer.serialize(this.newBook(book));
    }

    @Override
    public String updateOneFromJson(String book) throws ServiceException {
        this.serializer = (Serializer<BookDTO>) SerializersCatalog.getInstance(Serializers.JSON_BOOK);
        return serializer.serialize(this.updateBook(book));
    }

    @Override
    public String updateOneFromXml(String book) throws ServiceException {
        this.serializer = (Serializer<BookDTO>) SerializersCatalog.getInstance(Serializers.XML_BOOK);
        return serializer.serialize(this.updateBook(book));
    }

    @Override
    @Transactional
    public void deleteByIdProduct(String id) throws ServiceException {
        this.getByIdProduct(id);
        bookRepository.deleteByIdProduct(id);
    }
}
