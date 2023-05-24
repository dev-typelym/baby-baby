package com.app.babybaby.service.admin.adminParentsBoard;

import com.app.babybaby.domain.adminDTO.AdminParentsBoardDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.search.admin.AdminParentsBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminParentsBoardServiceImpl implements AdminParentsBoardService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParentsBoardRepository parentsBoardRepository;

    //    관리자 부모님마당 목록
    @Override
    public Page<AdminParentsBoardDTO> getAdminParentsBoardListWithPaging(int page, AdminParentsBoardSearch parentsBoardSearch) {
        Page<ParentsBoard> parentsBoards = parentsBoardRepository.findAllParentsBoardWithSearch_queryDSL(PageRequest.of(page, 5), parentsBoardSearch);
        List<AdminParentsBoardDTO> adminParentsBoardDTOS = parentsBoards.getContent().stream()
                .map(this::toAdminParentsBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminParentsBoardDTOS, parentsBoards.getPageable(), parentsBoards.getTotalElements());
    }

    //    관리자 부모님마당 상세
    @Override
    public AdminParentsBoardDTO getAdminParentsBoardById(Long boardId) {
        Optional<ParentsBoard> parentsBoard = parentsBoardRepository.findParentsBoardById_queryDSL(boardId);
        AdminParentsBoardDTO adminParentsBoardToDTO = toAdminParentsBoardDTO(parentsBoard.get());
        return adminParentsBoardToDTO;
    }

    //    관리자 부모님마당 삭제
    @Override
    public void deleteAdminParentsBoard(List<String> boardIds) {
        boardIds.stream().map(boardId -> Long.parseLong(boardId)).forEach(parentsBoardRepository::deleteAdminParentsBoardByIds_queryDSL);
    }
}
