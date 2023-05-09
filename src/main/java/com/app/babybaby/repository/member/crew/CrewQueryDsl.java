package com.app.babybaby.repository.member.crew;

import com.app.babybaby.entity.member.Kid;

import java.util.List;

public interface CrewQueryDsl {
    public List<Kid> findKidByUserId(Long id);
}
