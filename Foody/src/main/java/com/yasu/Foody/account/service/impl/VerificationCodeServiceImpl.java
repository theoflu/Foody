package com.yasu.Foody.account.service.impl;

import com.yasu.Foody.account.entity.EsVerificationCode;
import com.yasu.Foody.account.repository.EsVerificationCodeRepo;
import com.yasu.Foody.account.service.VerificationCodeService;
import com.yasu.Foody.security.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final PasswordEncoder passwordEncoder;
    private final EsVerificationCodeRepo esVerificationCodeRepo;
    @Override
    public String randomCode(Long id, String mail){
        Date currentDate = new Date();
        long c= id.toString().getBytes().length;
        long d= mail.getBytes().length;
        long e=passwordEncoder.encode(id+mail).getBytes().length;
        long f=passwordEncoder.encode(id.toString()).getBytes().length;
        long g=passwordEncoder.encode(mail).getBytes().length;
        long milliseconds = currentDate.getTime();
        long code=(c*d*f*g*milliseconds)/e;
        int intValue = (int) code;
        String asd=""+intValue;
        String middleFourDigits = asd.replaceAll(".*?(\\d{4}).*", "$1");
        save(id,middleFourDigits).block();
      return  middleFourDigits ;

          }
    private Mono<?> save(Long id, String code) {
        return esVerificationCodeRepo.findByUserId(id)
                .flatMap(existingCode -> {
                    EsVerificationCode esVerificationCode = EsVerificationCode.builder()
                            .id(existingCode.getId())
                            .userId(id)
                            .code(code)
                            .build();
                    return esVerificationCodeRepo.save(esVerificationCode);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    EsVerificationCode esVerificationCode = EsVerificationCode.builder()
                            .userId(id)
                            .code(code)
                            .build();
                    return esVerificationCodeRepo.save(esVerificationCode);
                }));
    }
}
