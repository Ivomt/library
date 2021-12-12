package com.aacademy.library.service.impl;

import com.aacademy.library.exception.ResourceNutFoundException;
import com.aacademy.library.model.Block;
import com.aacademy.library.model.Section;
import com.aacademy.library.repository.BlockRepository;
import com.aacademy.library.service.BlockService;
import com.aacademy.library.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BlockServiceImpl implements BlockService {
    private final BlockRepository blockRepository;
    private final SectionService sectionService;

    public BlockServiceImpl(BlockRepository blockRepository, SectionService sectionService) {
        this.blockRepository = blockRepository;
        this.sectionService = sectionService;
    }



    @Override
    public Block findById(Long id) {
        return blockRepository.findById(id)
                .orElseThrow(()->new ResourceNutFoundException(String.format("Block with id %d does not exists.",id)));
    }

    @Override
    public Block save(Block block) {
        Set<Section> sections = new HashSet<>();
        for (Section section : block.getSections()) {
            Section foundSection = sectionService.findById(section.getId());
            sections.add(foundSection);
        }
        return blockRepository.save(Block.builder()
                .name(block.getName())
                .sections(sections)
                .build());
    }

    @Override
    public Set<Block> findAll() {
        return null;
    }

    @Override
    public Block update(Block block, Long id) {
        Block foundBlock = findById(id);
        Block blockToUpdate=Block.builder()
                .id(foundBlock.getId())
                .sections(block.getSections())
                .build();
        return blockRepository.save(blockToUpdate);
    }

    @Override
    public void detachBlockSection(Long blockId, Set<Long> sectionIds) {
        Block foundBlock = findById(blockId);
        foundBlock.getSections()
                .removeIf(section -> sectionIds.contains(section.getId()));
        blockRepository.save(foundBlock);
    }
}
