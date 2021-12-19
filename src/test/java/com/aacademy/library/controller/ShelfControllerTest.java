package com.aacademy.library.controller;

import com.aacademy.library.converter.ShelfConverter;
import com.aacademy.library.dto.ShelfDto;
import com.aacademy.library.model.Shelf;
import com.aacademy.library.service.ShelfService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(value = ShelfController.class)
class ShelfControllerTest extends BaseControllerTest{

    @MockBean
    private ShelfService shelfService;

    @MockBean
    private ShelfConverter shelfConverter;

    @Test
    public void save() throws Exception{

        ShelfDto shelfDto = ShelfDto.builder().number(1).build();
        String reqJson = objectMapper.writeValueAsString(shelfDto);

        when(shelfConverter.toShelf(any(ShelfDto.class))).thenReturn(Shelf.builder().build());
        when(shelfService.save(any(Shelf.class))).thenReturn(Shelf.builder().build());
        when(shelfConverter.toShelfDto(any(Shelf.class))).thenReturn(ShelfDto.builder().id(1L).number(1).build());

        mockMvc.perform(post("/shelves")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())     //
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(1)));
    }
    @Test
    public void findById() throws Exception {

        when(shelfService.findById(any(Long.class))).thenReturn(Shelf.builder().build());
        when(shelfConverter.toShelfDto(any(Shelf.class))).thenReturn(ShelfDto.builder().id(1L).number(5).build());

        mockMvc.perform(get("shelves/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number", is(5)));
    }
    @Test
    public void deleteHappy() throws Exception {
        mockMvc.perform(delete("/shelves/1"))
                .andExpect(status().isOk());

    }
    @Test
    public void updateHappy() throws Exception {
        ShelfDto shelfDto = ShelfDto.builder().id(1L).number(2).build();
        String reqJson = objectMapper.writeValueAsString(shelfDto);

        when(shelfConverter.toShelfDto(any(Shelf.class))).thenReturn(shelfDto);
        mockMvc.perform(put("/shelves/1")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.number",is(2)));

    }
}