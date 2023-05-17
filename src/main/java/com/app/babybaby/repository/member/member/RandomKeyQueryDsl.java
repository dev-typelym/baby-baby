package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.RandomKey;

public interface RandomKeyQueryDsl {
    /* 회원의 가장 최근 랜덤키 찾기 */
    public RandomKey getLatestRandomKey(Long id);
}
