package com.app.babybaby.service.admin.adminNowKids;

import com.app.babybaby.domain.adminDTO.AdminKidDTO;
import com.app.babybaby.domain.adminDTO.AdminNowKidsDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.search.admin.AdminEventSearch;
import com.app.babybaby.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface AdminNowKidsService {

//    //    관리자 지금우리아이들은 목록
    public Page<AdminNowKidsDTO> getAdminEventListWithPaging(int page, AdminEventSearch eventSearch, CategoryType categoryType, String eventStatus);
//
//    //    관리자 지금우리아이들은 상세 보기
//    public AdminNowKidsDTO getAdminNowKidsById(Long nowKidsId);

    //    관리자 지금우리아이들은 삭제하기
    public void deleteAdminNowKids(List<Long> nowKidsIds);


    default AdminNowKidsDTO toAdminNowKidsDTO(NowKids nowKids){
        return AdminNowKidsDTO.builder()
                .id(nowKids.getId())
                .category(nowKids.getEvent().getCategory())
                .boardTitle(nowKids.getEvent().getBoardTitle())
                .guideName(nowKids.getGuide().getMemberName())
                .guideId(nowKids.getGuide().getId())
                .eventId(nowKids.getEvent().getId())
                .startDate(nowKids.getEvent().getCalendar().getStartDate())
                .endDate(nowKids.getEvent().getCalendar().getEndDate())
                .eventAddress(nowKids.getEvent().getEventLocation().getAddress())
                .eventAddressDetail(nowKids.getEvent().getEventLocation().getAddressDetail())
                .eventAddressSubDetail(nowKids.getEvent().getEventLocation().getAddressSubDetail())
                .nowKidsFileDTOS(nowKids.getNowKidsFiles().stream().map(nowKidsFile -> nowKidsFileToDTO(nowKidsFile)).collect(Collectors.toList()))
                .build();

    }

    default AdminKidDTO toAdminKidDTO(Kid kid){
        return AdminKidDTO.builder()
                .id(kid.getId())
                .kidAge(kid.getKidAge())
                .kidName(kid.getKidName())
                .kidAge(kid.getKidAge())
                .parentNickName(kid.getParent().getMemberNickname())
                .build();
    }

    default NowKidsFileDTO nowKidsFileToDTO(NowKidsFile nowKidsFile){
        return NowKidsFileDTO.orgFileBuilder()
                .fileOriginalName(nowKidsFile.getFileOriginalName())
                .filePath(nowKidsFile.getFilePath())
                .fileUUID(nowKidsFile.getFileUUID())
                .build();
    }
}
