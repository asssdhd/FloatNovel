package org.example.floatnovel.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookshelfVO {

    private Long novelId;

    private String novelName;

    private  String cover;
}
