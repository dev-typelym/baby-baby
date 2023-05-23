package com.app.babybaby.service.board.announcement;

import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.repository.board.announcement.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> find5RecentDesc() {
        List<Announcement> announcements = announcementRepository.find5RecentDesc();
        log.info(announcements.toString());
        List<Announcement> announcementList = announcements.stream()
                .collect(Collectors.toList());
        return announcementList;
    }
}
