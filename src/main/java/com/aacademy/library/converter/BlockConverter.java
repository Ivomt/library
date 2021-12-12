package com.aacademy.library.converter;

import com.aacademy.library.dto.BlockDto;
import com.aacademy.library.model.Block;
import com.aacademy.library.model.Section;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlockConverter {

    public BlockDto toBlockDto(Block block){
        return BlockDto.builder()
                .id(block.getId())
                .name(block.getName())
                .sectionIds(block.getSections().stream().map(Section::getId).collect(Collectors.toSet()))
                .build();
    }

    public Block toBlock(BlockDto blockDto){
        return Block.builder()
                .id(blockDto.getId())
                .name(blockDto.getName())
                .sections(blockDto.getSectionIds()
                        .stream()
                        .map(sectionId ->
                                Section.builder().id(sectionId  ).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
