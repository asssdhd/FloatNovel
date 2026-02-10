package org.example.floatnovel.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookshelf {

    private Long id;
    private Long userId;
    private Long novelId;
    private String novelName;
    private  String cover;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDeleted;

}
