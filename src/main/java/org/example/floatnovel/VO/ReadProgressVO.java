package org.example.floatnovel.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadProgressVO {

    private Long novelId;
    private Long chapterId;
    private Long offset;

}