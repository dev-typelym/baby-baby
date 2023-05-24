package com.app.babybaby.service.admin.adminParentsBoardReply;

import com.app.babybaby.domain.adminDTO.AdminParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.search.admin.AdminParentsBoardReplySearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminParentsBoardReplyService {
    //    관리자 부모님마당 댓글 목록
    public Page<AdminParentsBoardReplyDTO> getAdminParentsBoardReplyListWithPaging(int page, AdminParentsBoardReplySearch adminParentsBoardReplySearch);

    //    관리자 부모님마당 댓글 삭제하기
    public void deleteAdminParentsBoardReply(List<String> replyIds);

    default AdminParentsBoardReplyDTO toAdminParentsBoardReplyDTO(ParentsBoardReply parentsBoardReply){
        return AdminParentsBoardReplyDTO.builder()
                .id(parentsBoardReply.getId())
                .parentsBoardTitle(parentsBoardReply.getParentsBoard().getBoardTitle())
                .replyContent(parentsBoardReply.getParentsBoardReplyContent())
                .writeDate(parentsBoardReply.getRegisterDate())
                .build();
    }
}
