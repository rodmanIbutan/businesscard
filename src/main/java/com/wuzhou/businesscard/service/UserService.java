package com.wuzhou.businesscard.service;

import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;

public interface UserService {
    Status<User> login(User user);
    Status<Integer> updatePassword(String token,String password, String npassword);

}
