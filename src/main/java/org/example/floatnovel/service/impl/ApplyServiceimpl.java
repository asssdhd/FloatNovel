package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.common.ApplyStatus;
import org.example.floatnovel.common.LoginUser;
import org.example.floatnovel.constant.UserConstant;
import org.example.floatnovel.entity.Apply;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.entity.UserRole;
import org.example.floatnovel.mapper.ApplyMapper;
import org.example.floatnovel.mapper.UserMapper;
import org.example.floatnovel.mapper.UserRoleMapper;
import org.example.floatnovel.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;

@Service
public class ApplyServiceimpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {


    @Autowired
    private  ApplyMapper applyMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
      /*
   申请作者权限

    */
    public Result apply(Apply apply) {


        //填充字段
        Long userId = StpUtil.getLoginIdAsLong();
        apply.setUserId(userId);
        apply.setStatus(ApplyStatus.PENDING.getCode());
        apply.setCreateTime(LocalDateTime.now());

        //添加进数据库
        save(apply);





        return Result.success();
    }


      /*
   审核申请
   2025.12.17
  需要管理员权限
    */
    @Override
    public Result audit(Apply apply) {

        //更新审核结果
        applyMapper.audit(apply);

        //获取审核结果
        Integer status = apply.getStatus();

        //如果审核结果通过
        if (status == ApplyStatus.APPROVED.getCode()) {

        //给用户分配作者权限
        UserRole userRole = new UserRole();
        userRole.setUserId(apply.getUserId());
        userRole.setRoleId(UserConstant.ROLE_AUTHOR);

        userRoleMapper.insert(userRole);
    }
        //2把笔名写进用户表里
        userMapper.setUserNickName(apply);

        return Result.success();
    }

    /*
 查询申请列表
 2025.12.17
  */
    @Override
    public Page<Apply> applyPage(Integer pageNum, Integer pageSize) {

        int page = (pageNum == null || pageNum < 1) ? 1 : pageNum;
        int size = (pageSize == null || pageSize < 1) ? 10 : pageSize;

        Page<Apply> pages = new Page<>(page, size);

        LambdaQueryWrapper<Apply> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Apply::getCreateTime);

        return this.page(pages, wrapper);
    }
}
