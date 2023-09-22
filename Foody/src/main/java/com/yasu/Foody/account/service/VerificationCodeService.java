package com.yasu.Foody.account.service;

public interface VerificationCodeService {
    String randomCode(Long id, String mail);
}
