package org.example.floatnovel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueDTO {

    private Long chapterID;
    private String title;
    private Integer orders;
}
