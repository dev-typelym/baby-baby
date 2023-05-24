package com.app.babybaby.repository.member.crew;

import com.app.babybaby.entity.member.Kid;
import com.querydsl.core.Tuple;

import java.time.LocalDateTime;
import java.util.List;

public interface CrewQueryDsl {
    public List<Kid> findKidByUserId(Long id);

    public List<Tuple> findCrewByMemberId(Long memberId, String date);
}
