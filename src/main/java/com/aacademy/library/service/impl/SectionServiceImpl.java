package com.aacademy.library.service.impl;

import com.aacademy.library.exception.ResourceNutFoundException;
import com.aacademy.library.model.Section;
import com.aacademy.library.repository.SectionRepository;
import com.aacademy.library.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }


    @Override
    public Section findByName(String name) {
        return sectionRepository.findByName(name)
                .orElseThrow(()->new ResourceNutFoundException(String.format("Section with name: %s does not exists", name)));
    }

    @Override
    public Section findById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(()->new ResourceNutFoundException(String.format("Section with id: %s does not exists", id)));
    }

    @Override
    public Set<Section> findAll() {
        return new HashSet<>(sectionRepository.findAll());
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }
}
