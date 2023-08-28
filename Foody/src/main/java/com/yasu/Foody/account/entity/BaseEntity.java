package com.yasu.Foody.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private Date createdTime;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
