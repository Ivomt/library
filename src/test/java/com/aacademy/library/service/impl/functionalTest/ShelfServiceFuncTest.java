package com.aacademy.library.service.impl.functionalTest;

import com.aacademy.library.exception.DuplicateRecordException;
import com.aacademy.library.model.Shelf;
import com.aacademy.library.repository.ShelfRepository;
import com.aacademy.library.service.ShelfService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@SpringBootTest                         // functional test
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ShelfServiceFuncTest {
    @Autowired
    private ShelfService shelfService;

    @Autowired
    private ShelfRepository shelfRepository;

    @Test
    public void verifyUpdate() {

        Shelf shelf = Shelf.builder().number(1).build();
        Shelf savedShelf = shelfRepository.save(shelf);
        Shelf expected = Shelf.builder()
                .id(savedShelf.getId())
                .number(1)
                .build();

        Shelf actual = shelfService.update(savedShelf, savedShelf.getId());

        assertThat(expected.getId(), is(actual.getId()));
        assertThat(expected.getNumber(), is(actual.getNumber()));
    }
    @Test
    public void verifyFindById() {
        Shelf shelf = Shelf.builder().number(1).build();
        Shelf expected = shelfRepository.save(shelf);
        Shelf actual = shelfService.findById(expected.getId());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNumber(), actual.getNumber());
    }
    @Test
    public void verifyFindAll() {
        shelfRepository.saveAll(Arrays.asList(Shelf.builder().number(1).build(),
                Shelf.builder().number(2).build()));
        Set<Shelf> actual = shelfService.findAll();
        assertThat(actual.size(), is(2));
    }
    @Test
    public void verifySave() {
        Shelf savedShelf = shelfService.save(Shelf.builder().number(1).build());
        Optional<Shelf> actualFloor = shelfRepository.findById(savedShelf.getId());
        assertTrue(actualFloor.isPresent());
    }

    @Test
    public void verifyDeleteById() {
        Shelf savedShelf = shelfRepository.save(Shelf.builder().number(1).build());
        shelfService.delete(savedShelf.getId());
        List<Shelf> actual = shelfRepository.findAll();

        assertThat(actual.size(), is(0));
    }
    @Test
    public void verifyFindByNumber() {
        Shelf savedShelf = shelfRepository.save(Shelf.builder().number(1).build());
        Shelf actual = shelfService.findByNumber(1);
        assertEquals(actual.getNumber(), (savedShelf.getNumber()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByNumberException() {
        shelfService.findByNumber(1);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException() {

        shelfService.save(Shelf.builder().number(1).build());
        shelfService.save(Shelf.builder().number(1).build());
    }
}
