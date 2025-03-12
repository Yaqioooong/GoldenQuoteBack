package com.yaxingguo.goldenquote.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaxingguo.goldenquote.dto.QueryUserDto;
import com.yaxingguo.goldenquote.entity.User;
import com.yaxingguo.goldenquote.vo.QueryUserVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2025-02-01
 */
public interface UserMapper extends BaseMapper<User> {

    List<QueryUserVo> selectByCondition(QueryUserDto dto);
}
