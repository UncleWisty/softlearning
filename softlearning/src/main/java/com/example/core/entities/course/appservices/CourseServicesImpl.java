package com.example.core.entities.course.appservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.core.entities.course.dto.CourseDTO;
import com.example.core.entities.course.mapper.CourseMapper;
import com.example.core.entities.course.persistence.CourseRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.Serializers;
import com.example.services.serializers.SerializersCatalog;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.ServiceException;

@Controller
public class CourseServicesImpl implements CourseServices {

    @Autowired
    private CourseRepository courseRepository;
    private Serializer<CourseDTO> serializer;

    // ****** Implementing the business logic methods and common featues (clean code design) ******
    protected CourseDTO getDTO(String id) {
        return courseRepository.findByIdProduct(id).orElse(null);
    }

    protected CourseDTO getByIdProduct(String id) throws ServiceException {
        CourseDTO cdto = this.getDTO(id);

        if (cdto == null) {
            throw new ServiceException("course " + id + " not found");
        }
        return cdto;
    }

    protected CourseDTO checkInputData(String book) throws ServiceException {
        try {
            CourseDTO cdto = (CourseDTO) this.serializer.deserialize(book, CourseDTO.class);
            CourseMapper.DTOtoCourse(cdto);
            return cdto;
        } catch (BuildException e) {
            throw new ServiceException("error in the input course data: " + e.getMessage());
        }
    }



    protected CourseDTO getById(String id) throws ServiceException {
        return this.getByIdProduct(id);
    }

    protected CourseDTO newBook(String course) throws ServiceException {
        CourseDTO cdto = this.checkInputData(course);

        if (this.getDTO(cdto.getIdProduct()) == null) {
            return courseRepository.save(cdto);
        }
        throw new ServiceException("course " + cdto.getIdProduct() + " already exists");
    }

    protected CourseDTO updateBook(String course) throws ServiceException {
        CourseDTO cdto = this.checkInputData(course);
        this.getByIdProduct(cdto.getIdProduct());
        return courseRepository.save(cdto);
    }

    // ****** Implementing the interface methods ******

        @Override
    public String getAllCoursesToJson() throws ServiceException {
        Serializer<List<CourseDTO>> listSerializer = (Serializer<List<CourseDTO>>) SerializersCatalog.getInstance(Serializers.JSON_COURSE);
        return listSerializer.serialize(courseRepository.findAll());
    }


    @Override
    public String getByIdProductToJson(String id) throws ServiceException {
        this.serializer = (Serializer<CourseDTO>) SerializersCatalog.getInstance(Serializers.JSON_COURSE);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String addFromJson(String book) throws ServiceException {
        this.serializer = (Serializer<CourseDTO>) SerializersCatalog.getInstance(Serializers.JSON_COURSE);
        return serializer.serialize(this.newBook(book));
    }


    @Override
    public String updateOneFromJson(String book) throws ServiceException {
        this.serializer = (Serializer<CourseDTO>) SerializersCatalog.getInstance(Serializers.JSON_COURSE);
        return serializer.serialize(this.updateBook(book));
    }

    @Override
    @Transactional
    public void deleteByIdProduct(String id) throws ServiceException {
        this.getByIdProduct(id);
        courseRepository.deleteByIdProduct(id);
    }
}
