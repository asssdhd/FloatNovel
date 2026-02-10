package org.example.floatnovel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {

    private Long novelId;
    private Long chapterId;
    private String title;
    private Integer orders;
    private String content;

}
