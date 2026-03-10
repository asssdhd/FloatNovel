package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.entity.UserRole;

import java.util.List;

@Mapper
public interface UserRoleMapper  extends BaseMapper<UserRole> {

    public List<String> getRoles(Long userId);

    @Insert("INSERT INTO user_role(user_id,role_id) values (#{id},2)")
    void setRole(Long id);
    @Select("""
        SELECT r.name
        FROM role r
        JOIN user_role ur ON r.id = ur.role_id
        WHERE ur.user_id = #{userId}
    """)
    List<String> getRoleByUserId(Long userId);
}
