package com.aacademy.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ShelfDto {

    private Long id;

    @Range(min = 1, max = 100,message = "Shelf must be between 1 and 100")
    private Integer number;
}
