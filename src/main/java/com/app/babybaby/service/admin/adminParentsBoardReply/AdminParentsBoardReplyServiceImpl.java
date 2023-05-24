package com.app.babybaby.service.admin.adminParentsBoardReply;

import com.app.babybaby.domain.adminDTO.AdminParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.repository.reply.parentsBoardReply.ParentsBoardReplyRepository;
import com.app.babybaby.search.admin.AdminParentsBoardReplySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminParentsBoardReplyServiceImpl implements AdminParentsBoardReplyService {

    @Autowired
    private ParentsBoardReplyRepository parentsBoardReplyRepository;

    //    관리자 부모님마당 댓글 목록
    @Override
    public Page<AdminParentsBoardReplyDTO> getAdminParentsBoardReplyListWithPaging(int page, AdminParentsBoardReplySearch adminParentsBoardReplySearch) {
        Page<ParentsBoardReply> parentsBoardReplies = parentsBoardReplyRepository.findAlLParentsBoardReplyWithSearch_queryDSL(PageRequest.of(page, 5), adminParentsBoardReplySearch);
        List<AdminParentsBoardReplyDTO> adminParentsBoardDTOS = parentsBoardReplies.getContent().stream()
                .map(this::toAdminParentsBoardReplyDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminParentsBoardDTOS, parentsBoardReplies.getPageable(), parentsBoardReplies.getTotalElements());
    }

    //    관리자 부모님마당 댓글 삭제
    @Override
    public void deleteAdminParentsBoardReply(List<String> replyIds) {
        replyIds.stream().map(replyId -> Long.parseLong(replyId)).forEach(parentsBoardReplyRepository::deleteParentsBoardReplyByIds_queryDSL);

    }
}
