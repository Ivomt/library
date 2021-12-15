package com.aacademy.library.service.impl;

import com.aacademy.library.exception.DuplicateRecordException;
import com.aacademy.library.model.Shelf;
import com.aacademy.library.repository.ShelfRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShelfServiceTest {

    @Mock
    private ShelfRepository shelfRepository;

    @InjectMocks
    private ShelfServiceImpl shelfServiceImpl;

    @Test
    public void verifyFindAll(){
        when(shelfRepository.findAll()).thenReturn(Collections.emptyList());
        shelfServiceImpl.findAll();
        verify(shelfRepository, times(1)).findAll();
    }
    @Test
    public void verifySave() {
        Shelf shelfSave = Shelf.builder().build();
        when(shelfRepository.save(any(Shelf.class))).thenReturn(shelfSave);
        shelfServiceImpl.save(shelfSave);
        verify(shelfRepository, times(1)).save(shelfSave);
    }
    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDublicateException() {
        when(shelfRepository.save(any(Shelf.class))).thenThrow(DataIntegrityViolationException.class);
        shelfServiceImpl.save(Shelf.builder().build());
    }
    @Test
    public void verifyFindByNumber() {
        when(shelfRepository.findByNumber(any(Integer.class))).thenReturn(Optional.of(Shelf.builder().build()));
        shelfServiceImpl.findByNumber(1);
        verify(shelfRepository, times(1)).findByNumber(1);
    }
    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByNumberException() {
        when(shelfRepository.findByNumber(any(Integer.class))).thenReturn(Optional.empty());
        shelfServiceImpl.findByNumber(1);
    }
    @Test
    public void verifyFindById() {
        when(shelfRepository.findById(any(Long.class))).thenReturn(Optional.of(Shelf.builder().build()));
        shelfServiceImpl.findById(1L);
        verify(shelfRepository, times(1)).findById(1L);
    }
    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByIdException() {
        when(shelfRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        shelfServiceImpl.findById(1L);
    }
    @Test
    public void verifyDelete(){
        doNothing().when(shelfRepository).deleteById(any(Long.class));

        shelfServiceImpl.delete(1L);

        verify(shelfRepository,times(1)).deleteById(1L);

    }

}
