package com.aacademy.library.service;

import com.aacademy.library.model.Section;

import java.util.Set;

public interface SectionService {
    Section findByName(String name);

    Section findById(Long id);

    Set<Section> findAll();

    Section save(Section section);
}
