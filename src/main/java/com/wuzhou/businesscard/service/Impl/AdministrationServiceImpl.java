package com.wuzhou.businesscard.service.Impl;

import com.wuzhou.businesscard.entity.Card;
import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;
import com.wuzhou.businesscard.mapper.AdministrationMapper;
import com.wuzhou.businesscard.mapper.UserMapper;
import com.wuzhou.businesscard.service.AdministrationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wuzhou.businesscard.utils.JwtUtil.parseJWT;
import static java.lang.Integer.parseInt;

@Service
public class AdministrationServiceImpl implements AdministrationService {
    final
    AdministrationMapper administrationMapper;
    final UserMapper userMapper;

    public AdministrationServiceImpl(AdministrationMapper administrationMapper, UserMapper userMapper) {
        this.administrationMapper = administrationMapper;
        this.userMapper =userMapper;
    }


    public Status<List<User>> getUserList(String token) {
        int id = parseInt(parseJWT(token).get("id", String.class));
        if (id!=1){
            return new Status<>(false, "无权限", null);
        }
        return new Status<>(true, "查询成功", administrationMapper.selectUserList());
    }
    public Status<User> getUserById(Integer id,String token) {
        int userid = parseInt(parseJWT(token).get("id", String.class));
        if (userid!=1){
            return new Status<>(false, "无权限", null);
        }
        return new Status<>(true, "查询成功", administrationMapper.selectUserById(id));
    }
    public Status<Integer> createUser(User user,String token) {
        int userid = parseInt(parseJWT(token).get("id", String.class));
        if (userid!=1){
            return new Status<>(false, "无权限", null);
        }else {
           User checkUser =  userMapper.getUserByPhone(user.getPhone());
            if (checkUser!=null&&checkUser.getIsdeleted()==0){
                return new Status<>(false, "用户已存在", null);
            }
        }
        return new Status<>(true, "创建成功", administrationMapper.createUser(user));
    }
    public Status<Integer> updateUser(User user,String token) {
        int userid = parseInt(parseJWT(token).get("id", String.class));
        if (userid!=1){
            return new Status<>(false, "无权限", null);
        }
        return new Status<>(true, "更新成功", administrationMapper.updateUser(user));
    }
    public Status<Integer> deleteUser(Integer id,String token) {
        int userid = parseInt(parseJWT(token).get("id", String.class));
        if (userid!=1){
            return new Status<>(false, "无权限", null);
        }
        return new Status<>(true, "删除成功", administrationMapper.deleteUser(id));
    }
    public Status<List<Card>> getAllCard(String token){
        int userid = parseInt(parseJWT(token).get("id", String.class));
        if (userid!=1){
            return new Status<>(false, "无权限", null);
        }
        return new Status<List<Card>>(true,"获取成功",administrationMapper.selectAllCard());
    }
}
