package org.example.floatnovel.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    public List<String> getRoles(Long userId);

    @Insert("INSERT INTO user_role(user_id,role_id) values (#{id},2)")
    void setRole(Long id);
}
