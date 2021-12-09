package com.aacademy.library.repository;

import com.aacademy.library.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section>findByName(String name);   //метод за търсене на секции по име
}
