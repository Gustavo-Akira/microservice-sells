package br.com.gustavoakira.microsells.category.controller;

import br.com.gustavoakira.microsells.category.model.Category;
import br.com.gustavoakira.microsells.category.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    Category category = new Category("teste");;

    @Autowired
    private CategoryRepository repository;


    @AfterEach
    void afterEach() throws SQLException {
        repository.deleteAll();
    }


    @Test
    void shouldReturn404WhenGettingNonExistentCategory() throws Exception {
        mockMvc.perform(get("/api/v1/categories/1")).andExpect(status().isNotFound()).andExpect(jsonPath("timestamp").isString()).andExpect(jsonPath("code").value(404)).andExpect(jsonPath("status").value("NOT_FOUND")).andExpect(jsonPath("error").isArray());
    }

    @Test
    void shouldReturn404WhenDeletingNonExistentCategory() throws Exception {
        mockMvc.perform(delete("/api/v1/categories/1")).andExpect(status().isNotFound()).andExpect(jsonPath("timestamp").isString()).andExpect(jsonPath("code").value(404)).andExpect(jsonPath("status").value("NOT_FOUND")).andExpect(jsonPath("error").isArray());
    }

    @Test
    void shouldReturn400WhenSavingInvalidCategory() throws Exception {

        mockMvc.perform(post("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new Category()))).andExpect(status().is4xxClientError()).andExpect(jsonPath("code").value(400)).andExpect(jsonPath("status").value("BAD_REQUEST")).andExpect(jsonPath("error").isArray());;
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistentCategory() throws Exception {
        mockMvc.perform(put("/api/v1/categories/1").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(category))).andExpect(status().isNotFound()).andExpect(jsonPath("timestamp").isString()).andExpect(jsonPath("code").value(404)).andExpect(jsonPath("status").value("NOT_FOUND")).andExpect(jsonPath("error").isArray());
    }

    @Test
    void shouldReturn200AndPageEmptyWhenGetAllAndDoesNotHaveAnyCategory() throws Exception {
         mockMvc.perform(get("/api/v1/categories/")).andExpect(status().isOk()).andExpect(jsonPath("content").isArray());

    }
    @Test
    void shouldReturn201AndSaveCategoryWhenCategoryIsValid() throws Exception {
        mockMvc.perform(post("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(category))).andExpect(status().isCreated()).andExpect(jsonPath("name").value("teste"));
    }
    @Test
    void shouldReturn200AndPageWhenGetAllAndDoesHaveCategory() throws Exception {
        repository.save(category);
        mockMvc.perform(get("/api/v1/categories/")).andExpect(status().isOk()).andExpect(jsonPath("totalElements").value(1));
    }

    @Test
    void shouldReturn200AndCategoryWhenGettingExistentCategory() throws Exception {
        repository.save(category);
        mockMvc.perform(get("/api/v1/categories/1")).andExpect(status().isOk()).andExpect(jsonPath("name").value("teste"));
    }

    @Test
    void shouldReturn200WhenDeletingExistentCategory() throws Exception {
        Long id = repository.save(category).getId();
        mockMvc.perform(delete("/api/v1/categories/"+id)).andExpect(status().isOk());
    }

    @Test
    void shouldReturn200AndCategoryWhenUpdatingExistentCategory() throws Exception {

        Long id = repository.save(category).getId();
        Category updated = new Category(id,"updated");
        mockMvc.perform(put("/api/v1/categories/"+id).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updated))).andExpect(status().isOk()).andExpect(jsonPath("name").value("updated"));

    }

}
