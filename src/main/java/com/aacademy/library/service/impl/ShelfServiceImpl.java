package com.aacademy.library.service.impl;

import com.aacademy.library.exception.DuplicateRecordException;
import com.aacademy.library.exception.ResourceNutFoundException;
import com.aacademy.library.model.Shelf;
import com.aacademy.library.repository.ShelfRepository;
import com.aacademy.library.service.ShelfService;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;

    @Autowired
    public ShelfServiceImpl(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    @Override
    public Shelf save(Shelf shelf) {
        try {
            return shelfRepository.save(shelf);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateRecordException(String.format("Shelf with number %d already exists", shelf.getNumber()));
        }
    }

    @Override
    public Shelf findByNumber(Integer number) {
        return shelfRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNutFoundException(String.format("Shelf %d does not exists", number)));
    }

    @Override
    public Shelf findById(Long id) {
        return shelfRepository.findById(id) // проверява дали id го има в базата
                .orElseThrow(() -> new ResourceNutFoundException(String.format("Shelf with id %d does not exist", id)));
    }

    @Override
    public Shelf update(Shelf shelf, Long id) {
        Shelf foundShelf = findById(id);          //намира етажа от базата данни, който идва
        Shelf updatedShelf = Shelf.builder()   //създава нов обект
                .id(foundShelf.getId())        //слага неговото id
                .number(shelf.getNumber())     // слага номера, който искаш да се ъпдейтне
                .build();

        return save(updatedShelf);           // save
    }

    @Override
    public void delete(Long id) {
        shelfRepository.deleteById(id);
    }

    @Override
    public Set<Shelf> findAll() {
        return new HashSet<>(shelfRepository.findAll());
    }
}
