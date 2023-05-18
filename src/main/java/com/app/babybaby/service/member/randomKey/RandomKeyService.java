package com.app.babybaby.service.member.randomKey;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.RandomKey;

public interface RandomKeyService {

    /* 랜덤키 생성 */
    public RandomKey saveRandomKey(Member member);

    /* 가장 최근 랜덤키 불러오기 */
    public RandomKey getLatestRandomKey(Long id);
}
