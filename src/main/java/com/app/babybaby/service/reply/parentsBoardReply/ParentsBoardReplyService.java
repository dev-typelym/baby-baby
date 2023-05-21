package com.app.babybaby.service.reply.parentsBoardReply;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParentsBoardReplyService {

//    부모님 마당 댓글 목록
    public Page<ParentsBoardReplyDTO> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id);

//    부모님 마당 댓글 추가
//    public void parentsBoardReplySave(HttpSession httpSession,String replyContent, Long parentsBoardId);

//    부모님 마당 댓글 삭제
    public void removeByParentsBoardReply(Long parentsBoardReplyId);

//    아이디로 해당 멤버 찾기
//    public void findById(Long id);


//    부모님 마당 댓글 수정
    public void updateByParentsBoardReply(Long replyId, String replyContent);

    default ParentsBoardReplyDTO parentsBoardReplyToDTO(ParentsBoardReply parentsBoardReply) {
        return ParentsBoardReplyDTO.builder()
                .id(parentsBoardReply.getId())
                .registerDate(parentsBoardReply.getRegisterDate())
                .updateDate(parentsBoardReply.getUpdateDate())
                .parentsBoardReplyContent(parentsBoardReply.getParentsBoardReplyContent())
                .memberId(parentsBoardReply.getMember().getId())
                .memberNickName(parentsBoardReply.getMember().getMemberNickname())
                .memberFileOriginalName(parentsBoardReply.getMember().getMemberFileOriginalName())
                .memberFilePath(parentsBoardReply.getMember().getMemberFilePath())
                .memberFileUUID(parentsBoardReply.getMember().getMemberFileUUID())
                .parentsBoardId(parentsBoardReply.getParentsBoard().getId())
                .build();
    }

    default ParentsBoardReply parentsBoardReplyToEntity(ParentsBoardReplyDTO parentsBoardReplyDTO) {
        return ParentsBoardReply.builder()
                .parentsBoardReplyContent(parentsBoardReplyDTO.getParentsBoardReplyContent())
                .build();
    }

//    댓글 추가
    public ParentsBoardReplyDTO parentsBoardReplySave(ParentsBoardReplyDTO parentsBoardReplyDTO, Long memberId, Long parentsBoardId);
}
