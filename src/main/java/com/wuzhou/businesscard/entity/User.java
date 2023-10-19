package com.wuzhou.businesscard.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private Integer gender;
    private String avatar;
    private String jwt;
    private Integer isdeleted;
}
