package com.yasu.Foody.account.entity.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserDeleteReq {
   private Long id;
   private String message;
}
