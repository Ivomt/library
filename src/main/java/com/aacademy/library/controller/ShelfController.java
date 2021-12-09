package com.aacademy.library.controller;

import com.aacademy.library.converter.ShelfConverter;
import com.aacademy.library.dto.ShelfDto;
import com.aacademy.library.model.Shelf;
import com.aacademy.library.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/shelves")
public class ShelfController {

    private final ShelfService shelfService;
    private final ShelfConverter shelfConverter;

    @Autowired
    public ShelfController(ShelfService shelfService, ShelfConverter shelfConverter) {
        this.shelfService = shelfService;
        this.shelfConverter=shelfConverter;
    }

    @GetMapping
    public ResponseEntity<Set<ShelfDto>> findAll(){
        Set<ShelfDto> shelfDtos = new HashSet<>();
        Set<Shelf> shelves = shelfService.findAll();   // колекция, която се връща от сървиса

        for (Shelf shelf:shelves){
            ShelfDto shelfDto = shelfConverter.toShelfDto(shelf);
            shelfDtos.add(shelfDto);
        }
        return ResponseEntity.ok(shelfDtos);

    }
    @PostMapping
    public ResponseEntity<ShelfDto>save (@RequestBody @Valid ShelfDto shelfDto) {
        Shelf shelf = shelfConverter.toShelf(shelfDto);
        Shelf savedShelf = shelfService.save(shelf);
        return ResponseEntity.ok(shelfConverter.toShelfDto(savedShelf));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus>delete(@PathVariable Long id){
        shelfService.delete(id);
        return ResponseEntity.ok().build();
    }
}
