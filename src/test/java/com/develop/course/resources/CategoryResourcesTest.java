package com.develop.course.resources;

import com.develop.course.entities.Category;
import com.develop.course.repository.CategoryRepository;
import com.develop.course.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryResourcesTest {

    public static final Long ID = 1L;
    public static final String NAME = "Books";
    public static final int INDEX = 0;

    @InjectMocks
    CategoryResources resources;

    @Mock
    CategoryService service;

    @Mock
    CategoryRepository repository;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCategory();
    }

    @Test
    void whenFindAllReturnListOfCategory() {
        when(service.findAll()).thenReturn(List.of(category));

        ResponseEntity<List<Category>> response = resources.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenFindByIdThenReturnOnlyOneCategory() {
        when(service.findById(any())).thenReturn(category);

        ResponseEntity<Category> response = resources.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());

    }

    @Test
    void whenInsertThenReturnSuccess() {
        when(service.insert(any())).thenReturn(category);

        ResponseEntity<Category> response = resources.insert(category);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void delete() {
    }

    private void startCategory() {
        category = new Category(ID, NAME);
    }
}