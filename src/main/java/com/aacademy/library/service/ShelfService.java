package com.aacademy.library.service;

import com.aacademy.library.model.Shelf;

import java.util.Set;

public interface ShelfService {  //тук се създават CRUD операции
    Shelf save (Shelf shelf);

    Shelf findByNumber(Integer number);

    Shelf findById (Long id);

    Shelf update (Shelf shelf, Long id);

    void delete (Long id);

    Set<Shelf> findAll();
}
