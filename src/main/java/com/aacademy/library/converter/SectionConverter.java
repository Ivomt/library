package com.aacademy.library.converter;

import com.aacademy.library.dto.SectionDto;
import com.aacademy.library.dto.ShelfDto;
import com.aacademy.library.model.Section;
import com.aacademy.library.model.Shelf;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter {

    public SectionDto toSectionDto (Section section) {
        return SectionDto.builder()
                .id(section.getId())
                .name(section.getName())
                .build();
    }
    public Section toSection(SectionDto sectionDto){
        return Section.builder()
                .id(sectionDto.getId())
                .name(sectionDto.getName())
                .build();
    }
}
