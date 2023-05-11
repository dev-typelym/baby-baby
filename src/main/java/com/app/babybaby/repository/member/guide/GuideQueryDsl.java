package com.app.babybaby.repository.member.guide;

import com.app.babybaby.entity.member.Guide;

import java.util.List;

public interface GuideQueryDsl {
//    가이드 통솔 목록 조회
    public List<Guide> findEventById(Long guideId,Long eventId);
}
