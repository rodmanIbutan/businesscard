package com.wuzhou.businesscard.service.Impl;

import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;
import com.wuzhou.businesscard.mapper.UserMapper;
import com.wuzhou.businesscard.service.UserService;
import com.wuzhou.businesscard.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wuzhou.businesscard.utils.JwtUtil.parseJWT;
import static java.lang.Integer.parseInt;

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

    @Override
    public Status<Integer> updatePassword(String token, String password, String npassword) {
        int id = parseInt(parseJWT(token).get("id", String.class));
        System.out.println(id);
        System.out.println(npassword);
        User user = userMapper.getUserById(id);
        if (user == null) {
            return new Status<Integer>(false, "用户不存在", null);
        } else if (!user.getPassword().equals(password)) {
            return new Status<Integer>(false, "密码错误", null);
        } else {
            return new Status<Integer>(true, "修改成功", userMapper.updatePassword(id, npassword));
        }
    }
}
