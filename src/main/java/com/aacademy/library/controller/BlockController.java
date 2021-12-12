package com.aacademy.library.controller;

import com.aacademy.library.converter.BlockConverter;
import com.aacademy.library.dto.BlockDetachSectionDto;
import com.aacademy.library.dto.BlockDto;
import com.aacademy.library.model.Block;
import com.aacademy.library.service.BlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/blocks")
public class BlockController {

    private final BlockService blockService;
    private final BlockConverter blockConverter;

    public BlockController(BlockService blockService, BlockConverter blockConverter) {
        this.blockService = blockService;
        this.blockConverter = blockConverter;
    }
    @PostMapping
    public ResponseEntity<BlockDto> save(@RequestBody BlockDto blockDto) {
        Block block = blockConverter.toBlock(blockDto);
        Block savedBlock = blockService.save(block);
        BlockDto blockDtoResponse = blockConverter.toBlockDto(savedBlock);
        return ResponseEntity.ok(blockDtoResponse);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BlockDto>findById(@PathVariable Long id){
        Block foundBlock = blockService.findById(id);
        BlockDto blockDto = blockConverter.toBlockDto(foundBlock);
        return ResponseEntity.ok(blockDto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<BlockDto>update(@RequestBody BlockDto blockDto,
                                         @PathVariable Long id){
        Block block = blockConverter.toBlock(blockDto);
        Block savedBlock = blockService.update(block,id);
        BlockDto blockDtoResponse = blockConverter.toBlockDto(savedBlock);
        return ResponseEntity.ok(blockDtoResponse);

    }
    @PutMapping(value = "/detach")
    private ResponseEntity<HttpStatus>detach(@RequestBody BlockDetachSectionDto blockDetachSectionDto){
        blockService.detachBlockSection(blockDetachSectionDto.getBlockId(),blockDetachSectionDto.getSectionIds()    );
        return ResponseEntity.ok().build();
    }
}
