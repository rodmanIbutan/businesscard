package com.wuzhou.businesscard.service.Impl;

import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;
import com.wuzhou.businesscard.mapper.UserMapper;
import com.wuzhou.businesscard.service.UserService;
import com.wuzhou.businesscard.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Status<User> login(User user) {
        User Luser = userMapper.getUserByPhone(user.getPhone());
        if (Luser == null) {
            return new Status<User>(false, "用户不存在", null);
        } else if (!Luser.getPassword().equals(user.getPassword())) {
            return new Status<User>(false, "密码错误", null);
        } else {
            String jwt = JwtUtil.generateJwt(String.valueOf(Luser.getId()));
            Luser.setJwt(jwt);
            return new Status<User>(true, "登录成功", Luser);
        }
    }
}
