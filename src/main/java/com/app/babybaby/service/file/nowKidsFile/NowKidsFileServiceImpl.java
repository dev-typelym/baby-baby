package com.app.babybaby.service.file.nowKidsFile;

import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.file.nowKidsFile.NowKidsFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NowKidsFileServiceImpl implements NowKidsFileService {

    private final NowKidsFileRepository nowKidsFileRepository;

    private final EventRepository eventRepository;

    private final NowKidsRepository nowKidsRepository;

    @Override
    public Event getBoardInfoByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return event;
    }

    @Override
    public void saveAllFiles(List<NowKidsFileDTO> files, Long nowKidsId) {
        List<NowKidsFile> nowKidsFiles = files.stream()
                .peek(nowKidsFileDTO -> nowKidsFileDTO.setNowKidsId(nowKidsId))
                .map(this::toNowKidsFileEntity)
                .collect(Collectors.toList());
        NowKids nowKids = nowKidsRepository.findById(nowKidsId).get();
        nowKidsFiles.stream().peek(nowKidsFile -> nowKidsFile.setNowKids(nowKids)).collect(Collectors.toList());
        nowKidsFileRepository.saveAll(nowKidsFiles);
    }
}
