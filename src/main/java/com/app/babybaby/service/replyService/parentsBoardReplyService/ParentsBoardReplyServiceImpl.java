package com.app.babybaby.service.replyService.parentsBoardReplyService;

import com.app.babybaby.domain.replyDTO.parentsBoardReplyDTO.ParentsBoardReplyDTO;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import com.app.babybaby.repository.reply.parentsBoardReply.ParentsBoardReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("parentsBoardReply")
public class ParentsBoardReplyServiceImpl implements ParentsBoardReplyService {
    private final ParentsBoardReplyRepository parentsBoardReplyRepository;


    
//    댓글 목록 불러오기
    @Override
    public Slice<ParentsBoardReplyDTO> findAllReplyByBoardIdWithPaging(Pageable pageable, Long id) {
        Slice<ParentsBoardReply> parentsBoardReplySlice = parentsBoardReplyRepository.findAllReplyByBoardIdWithPaging(pageable, id);
        List<ParentsBoardReplyDTO> parentsBoardReplyDTOList = parentsBoardReplySlice
                .get()
                .map(this::ParentsBoardReplyToDTO)
                .collect(Collectors.toList());
        return new SliceImpl<>(parentsBoardReplyDTOList, pageable, parentsBoardReplySlice.hasNext());
    }
}
