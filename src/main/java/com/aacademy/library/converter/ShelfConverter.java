package com.aacademy.library.converter;

import com.aacademy.library.dto.ShelfDto;
import com.aacademy.library.model.Shelf;
import org.springframework.stereotype.Component;

@Component
public class ShelfConverter {

    public ShelfDto toShelfDto (Shelf shelf) {  //от модела Shelf искаме да върнем ShelfDto,
        return ShelfDto.builder()              // конвертираме един клас в друг
                .number(shelf.getNumber())
                .build();
    }
    public Shelf toShelf(ShelfDto shelfDto){
        return Shelf.builder()
                .number(shelfDto.getNumber())
                .build();
    }
}
