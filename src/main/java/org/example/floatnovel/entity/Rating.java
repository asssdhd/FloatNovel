package org.example.floatnovel.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private Long id;

    private Long userId;

    private Long novelId;

    private Integer score;

    private LocalDate createTime;
}
