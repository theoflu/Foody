package com.yasu.Foody.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messag extends RuntimeException {
    private String content;
    private String error;

}