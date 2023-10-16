package com.yasu.Foody.rabbitmq.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
    private String notificationID;
    private String message;
    private Date createAt;
    private Boolean seen;
}
