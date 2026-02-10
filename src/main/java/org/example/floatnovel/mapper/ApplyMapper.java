package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.floatnovel.entity.Apply;
@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {

    @Update("update author_apply set status=#{status},audit_reason=#{auditReason},audit_time=#{auditTime} where id=#{id}")
    void audit(Apply apply);
}
