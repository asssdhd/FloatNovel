package org.example.floatnovel.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterContent {

    private Long chapter_id;

    private String content;
}
