package org.example.floatnovel.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long chapterId;
    private String title;
    private Integer orders;
}
