package com.app.babybaby.service.admin.adminAskService;

import com.app.babybaby.domain.adminDTO.AdminAskDTO;
import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.search.admin.AdminAskSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminAskService {

    //    관리자 문의 목록
    public Page<AdminAskDTO> getAdminAskListWithPaging(int page, AdminAskSearch adminAskSearch);

    //    관리자 문의 상세 보기
    public List<AdminAskDTO> getAdminAskDetail();

    //    관리자 문의 삭제하기
    public void deleteAdminAsk(List<String> askIds);


    default AdminAskDTO toAskDTO(Ask ask){


        return AdminAskDTO.builder()
                .id(ask.getId())
                .askContent(ask.getBoardContent())
                .askTitle(ask.getBoardTitle())
                .writeDate(ask.getRegisterDate())
                .writerName(ask.getMember().getMemberName())
                .build();
    }

}
