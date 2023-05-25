package com.app.babybaby.service.member.kid;

import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.member.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public interface KidService {

    public List<KidDTO> findAllKidsByMemberIdAndEventId(Long sessionId, Long eventId);
    public void save(KidDTO kidDTO);
    public List<KidDTO> findALlMyKid(Long memberId);
    public Long findALlMyKidCount(Long memberId);

    default KidDTO toKidDTO(Kid kid){
        return KidDTO.builder()
                .id(kid.getId())
                .kidAge(kid.getKidAge())
                .kidGender(kid.getKidGender())
                .kidName(kid.getKidName())
                .kidAge(kid.getKidAge())
                .build();
    }

    default Kid toKid(KidDTO kidDTO){
        return Kid.builder()
                .id(kidDTO.getId())
                .kidAge(kidDTO.getKidAge())
                .kidGender(kidDTO.getKidGender())
                .kidName(kidDTO.getKidName())
                .kidAge(kidDTO.getKidAge())
                .parent(kidDTO.getParent())
                .build();
    }

}
