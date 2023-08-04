package com.develop.course.services;

import com.develop.course.entities.Category;
import com.develop.course.repository.CategoryRepository;
import com.develop.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Books";
    public static final int INDEX = 0;
    public static final String RESOURCE_NOT_FOUND_ID = "Resource not found. Id ";

    @InjectMocks
    private CategoryService service;

    @Mock
    CategoryRepository repository;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCategory();
    }

    @Test
    void whenFindAllThenReturnListOfCategory() {
        when(repository.findAll()).thenReturn(List.of(category));

        List<Category> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Category.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
    }

    @Test
    void whenFindByIdThenReturnOneCategory() {
        when(repository.findById(ID)).thenReturn(Optional.of(category));

        Category response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Category.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenFindByIdThenReturnNotFoundException() {
        when(repository.findById(ID)).thenThrow(new ResourceNotFoundException(ID));

        try {
            service.findById(ID);
        } catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals(RESOURCE_NOT_FOUND_ID + ID, e.getMessage());
        }
    }

    @Test
    void whenInsertThenReturnSuccess() {
        when(repository.save(any())).thenReturn(category);

        Category response = service.insert(category);

        assertNotNull(response);
        assertEquals(Category.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(repository).deleteById(any());

        service.delete(ID);

        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void whenDeleteThenReturnResourceNotFoundException() {
        doNothing().when(repository).deleteById(any());
        when(repository.findById(ID)).thenThrow(new ResourceNotFoundException(ID));

        try {
            service.delete(ID);
        } catch (EmptyResultDataAccessException e) {
            assertEquals(RESOURCE_NOT_FOUND_ID, e.getMessage());
            assertEquals(ResourceNotFoundException.class, e.getClass());
        }

        verify(repository, times(1)).deleteById(any());
    }


    private void startCategory() {
        category = new Category(ID, NAME);
    }
}