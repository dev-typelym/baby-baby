package com.app.babybaby.repository.member.crew;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Kid;

import java.util.List;

public interface CrewQueryDsl {
    public List<Kid> findKidByUserId(Long id);

    public List<Crew> findCrewByMemberId(Long memberId,String date);
}
