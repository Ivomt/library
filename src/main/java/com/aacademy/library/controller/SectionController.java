package com.aacademy.library.controller;

import com.aacademy.library.converter.SectionConverter;
import com.aacademy.library.dto.SectionDto;
import com.aacademy.library.model.Section;
import com.aacademy.library.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class SectionController {

    private final SectionService sectionService;
    private final SectionConverter sectionConverter;

    @Autowired
    public SectionController(SectionService sectionService, SectionConverter sectionConverter) {
        this.sectionService = sectionService;
        this.sectionConverter = sectionConverter;
    }
    @GetMapping
    public ResponseEntity<Set<SectionDto>>findAll(){
        return ResponseEntity.ok(sectionService
                .findAll()
                .stream()
                .map(sectionConverter::toSectionDto)
                .collect(Collectors.toSet()));
    }
    @GetMapping(value = "/{name}")
    public ResponseEntity<SectionDto>findByName(@PathVariable String name) {
        Section section = sectionService.findByName(name);
        SectionDto sectionDto = sectionConverter.toSectionDto(section);
        return ResponseEntity.ok(sectionDto);
    }
    @PostMapping
    public ResponseEntity<SectionDto>save(@RequestBody SectionDto sectionDto){
        return ResponseEntity.ok(sectionConverter.toSectionDto
                (sectionService.save(sectionConverter.toSection(sectionDto))));
    }
}
