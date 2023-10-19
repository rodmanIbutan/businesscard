package com.wuzhou.businesscard.service;

import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;

import java.util.List;

public interface AdministrationService {
    Status<List<User>> getUserList(String token);
    Status<User> getUserById(Integer id,String token);
    Status<Integer> createUser(User user,String token);
    Status<Integer> updateUser(User user,String token);
    Status<Integer> deleteUser(Integer id,String token);
}
