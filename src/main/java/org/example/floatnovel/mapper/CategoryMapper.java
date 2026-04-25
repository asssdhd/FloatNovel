package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.DTO.CategoryDTO;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<CategoryDTO> {

    @Select("select  id ,name from  category")
    List<CategoryDTO> getCategoryList();
}
