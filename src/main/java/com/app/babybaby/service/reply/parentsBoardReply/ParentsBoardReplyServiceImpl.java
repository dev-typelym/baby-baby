package com.app.babybaby.service.reply.parentsBoardReply;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.repository.reply.parentsBoardReply.ParentsBoardReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("parentsBoardReply")
public class ParentsBoardReplyServiceImpl implements ParentsBoardReplyService {
    private final ParentsBoardReplyRepository parentsBoardReplyRepository;
    private final ParentsBoardRepository parentsBoardRepository;
    private final MemberRepository memberRepository;


    
//    댓글 목록 불러오기
    @Override
    public Page<ParentsBoardReplyDTO> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id) {
        Page<com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply> parentsBoardReply = parentsBoardReplyRepository.findAllReplyByBoardIdWithPaging(pageable, id);
        List<ParentsBoardReplyDTO> parentsBoardReplyDTOList = parentsBoardReply
                .get()
                .map(this::ParentsBoardReplyToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(parentsBoardReplyDTOList, pageable, parentsBoardReply.getTotalElements());
    }

    @Override
    public void parentsBoardReplySave(Long SessionId, ParentsBoardReplyDTO parentsBoardReplyDTO, Long parentsBoardId) {
        Member member = memberRepository.findById(SessionId).get(); // 아이디로 찾은 멤버

        ParentsBoard parentsBoard = parentsBoardRepository.findById(parentsBoardId).get();

        ParentsBoardReply parentsBoardReply = parentsBoardReplyToEntity(parentsBoardReplyDTO);

        ParentsBoardReply parentsBoardReply2 = new ParentsBoardReply(parentsBoardReplyDTO.getParentsBoardReplyContent(), parentsBoard, member);

        parentsBoardReplyRepository.save(parentsBoardReply2);
    }


}
