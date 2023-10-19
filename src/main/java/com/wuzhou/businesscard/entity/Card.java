package com.wuzhou.businesscard.entity;

import lombok.Data;

@Data
public class Card {
    private Integer id;
    private Integer userId;
    private String name;
    private String title;
    private String remarks;
    private String phone;
    private String email;
    private String telephone;
    private String address;
    private String position;
    private int company;
}
