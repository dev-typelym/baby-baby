package com.app.babybaby.service.member.kid;

import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.entity.member.Kid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KidService {

    public List<KidDTO> findAllKidsByMemberIdAndEventId(Long sessionId, Long eventId);

    default KidDTO toKidDTO(Kid kid){
        return KidDTO.builder()
                .id(kid.getId())
                .kidAge(kid.getKidAge())
                .kidGender(kid.getKidGender())
                .kidName(kid.getKidName())
                .kidAge(kid.getKidAge())
                .parentId(kid.getParent().getId())
                .build();
    }
}
