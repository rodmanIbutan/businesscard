package com.wuzhou.businesscard.mapper;

import com.wuzhou.businesscard.entity.Card;
import com.wuzhou.businesscard.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdministrationMapper {
    List<User> selectUserList();

    User selectUserById(@Param("id") Integer id);

    Integer createUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(@Param("id") Integer id);
    List<Card> selectAllCard();


}
