package com.app.babybaby.service.member.guide;

import com.app.babybaby.entity.member.Kid;

import java.util.List;

public interface GuideService {
    public void processPayment(Long memberId, Long eventId, List<Kid> kids);

    public Boolean applyGuide(Long memberId, Long eventId);
}
