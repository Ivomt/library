package com.aacademy.library.repository;

import com.aacademy.library.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    Optional<Shelf>findByNumber(Integer number);  // метод за търсене на рафтове по номера
}
