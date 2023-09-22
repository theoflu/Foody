package com.yasu.Foody.account.repository;

import com.yasu.Foody.account.entity.EsVerificationCode;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Mono;

public interface EsVerificationCodeRepo extends ReactiveElasticsearchRepository<EsVerificationCode,String> {
    Mono<EsVerificationCode> findByUserId(Long id);
    Mono<EsVerificationCode> deleteByUserId(Long id);
}
