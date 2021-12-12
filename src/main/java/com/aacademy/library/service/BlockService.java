package com.aacademy.library.service;

import com.aacademy.library.model.Block;

import java.util.Set;

public interface BlockService {
    Block findById(Long id);
    Block save (Block block);
    Set<Block> findAll();
    Block update (Block block, Long id);
    void detachBlockSection(Long blockId,Set<Long>sectionIds);
}
