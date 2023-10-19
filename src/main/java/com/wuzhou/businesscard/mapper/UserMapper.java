package com.wuzhou.businesscard.mapper;

import com.wuzhou.businesscard.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUserByPhone(@Param("phone") String phone);
}
