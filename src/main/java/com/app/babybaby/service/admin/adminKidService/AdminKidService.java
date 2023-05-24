package com.app.babybaby.service.admin.adminKidService;

import com.app.babybaby.domain.adminDTO.AdminKidDTO;
import com.app.babybaby.entity.member.Kid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminKidService {

    public List<AdminKidDTO> findAllKidsByGuideIdAndEventId(Long guidId, Long eventId);

    default AdminKidDTO toKidDTO(Kid kid){
        return AdminKidDTO.builder()
                .id(kid.getId())
                .parentNickName(kid.getParent().getMemberNickname())
                .kidName(kid.getKidName())
                .kidAge(kid.getKidAge())
                .kidGender(kid.getKidGender())
                .build();
    }
}
