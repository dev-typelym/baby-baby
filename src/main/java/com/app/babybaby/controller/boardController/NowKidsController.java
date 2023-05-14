package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.file.nowKidsFile.NowKidsFileRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import com.app.babybaby.service.board.nowKids.NowKidsService;
import com.app.babybaby.service.file.nowKidsFile.NowKidsFileService;
import com.app.babybaby.service.member.kid.KidService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/nowKid/*")
public class NowKidsController {

    private final NowKidsService nowKidsService;

    private final KidService kidService;

    private final NowKidsFileService nowKidsFileService;

    private final NowKidsRepository nowKidsRepository;

    private final MemberRepository memberRepository;

    private final EventRepository eventRepository;

    private final NowKidsFileRepository nowKidsFileRepository;

    @GetMapping("write")
    public String goWriteNowKids(Model model, RedirectAttributes redirectAttributes) {
        Long sessionId = 1L;
        Member member = memberRepository.findById(sessionId).get();
        JSONObject memberJSON = new JSONObject();
        List<Tuple> nowKidsEvents = nowKidsRepository.findEventAndCalendarInfoByGuideId_QueryDsl(sessionId);
        JSONArray calendars = new JSONArray();
        JSONArray events = new JSONArray();

        for (Tuple tuple : nowKidsEvents) {
            Event event = tuple.get(0, Event.class);
            Calendar calendar = tuple.get(1, Calendar.class);


            JSONObject eventJSON = new JSONObject();
            eventJSON.put("eventTitle", event.getBoardTitle());
            eventJSON.put("eventId", event.getId());
            eventJSON.put("eventCategory", event.getCategory());
            eventJSON.put("eventId", event.getId());

            JSONObject calendarJSON = new JSONObject(calendar);
            events.put(eventJSON);
            calendars.put(calendarJSON);
        }
//        memberJSON.put("memberNickname", member.getMemberNickname());
//        log.info(events.toString());
//        log.info(calendars.toString());
        log.info("eventTITLE가져오기 : " + events);
        log.info("======================================================================");
        model.addAttribute("calendars", calendars.toString());
        model.addAttribute("eventTitle", events.toString());
        model.addAttribute("member", member.getMemberNickname());

        return "/nowKids/now-kids-write";
    }



    @PostMapping("getKids")
    @ResponseBody
    public String getAllKids(Long sessionId, Long eventId) {
        sessionId = 1L;
        log.info("eventID는 : " + eventId.toString());
        log.info("Kids들은" + nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(sessionId, eventId).toString());
        JSONArray jsonArray = new JSONArray();

        List<KidDTO> kids = kidService.findAllKidsByMemberIdAndEventId(sessionId, eventId);
        kids.forEach(kid -> {
            JSONObject jsonObject = new JSONObject(kid);
            jsonArray.put(jsonObject);
        });
        return jsonArray.toString();
    }





    @GetMapping("multi")
    public String writeNowKidFiles(Long eventId, String eventDate, Model model){
        Long sessionId = 1L;
        log.info(eventDate.toString());
        log.info(eventId.toString());

        Member member = memberRepository.findById(sessionId).get();
        Event event = eventRepository.findById(eventId).get();
        log.info(member.getMemberNickname());

        model.addAttribute("memberNickName", member.getMemberNickname());
        model.addAttribute("eventCategory", event.getCategory());
        model.addAttribute("eventId", eventId);

//        for (int i = 0; i < nowKidsFileDTOS.size(); i++){
//            nowKidsFileService.
//        }

        return "nowKids/now-kids-write-multi";
    }


    @GetMapping("save")
    public RedirectView saveAllNowKids(Long eventId, String eventDate, NowKidsDTO nowKidsDTO){
        Long sessionId = 1L;
        log.info("eventDate는 : " + eventDate);
        log.info("EventId는 : " + eventId.toString());

        Event event = nowKidsFileService.getBoardInfoByEventId(eventId);
        Member member = memberRepository.findById(sessionId).get();
        NowKids nowKids = new NowKids(event, member);
        NowKids nowKids1 = nowKidsRepository.save(nowKids);

//        nowKidsFileService.saveAllFiles(nowKidsDTO.getFiles(), nowKids1.getId());
//        nowKidsDTO.getFiles().stream().map(NowKidsFileDTO::toString).forEach(log::info);

        return new RedirectView("/nowKid/list");
    }



    @GetMapping("list")
    public String goNowKidsList(Long sessionId, Model model, Pageable pageable){
        int pageNum = pageable.getPageNumber();
        sessionId = 1L;
        Page<NowKidsDTO> nowKidsDTOS = nowKidsService.getAllInfoForListDesc(1, 5);
        JSONArray jsonArray = new JSONArray();

        nowKidsDTOS.forEach(nowKidsDTO -> {
            List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(nowKidsDTO.getMemberId(), nowKidsDTO.getEventId());
            List<NowKidsFile> nowKidsFiles = nowKidsRepository.findAllFileNowKidsById_QueryDsl(nowKidsDTO.getNowKidsId());
            List<KidDTO> kidDTOS = kids.stream().map(kidService::toKidDTO).collect(Collectors.toList());
            List<NowKidsFileDTO> nowKidsFileDTOS = nowKidsFiles.stream().map(nowKidsFileService::toNowKidsFileDTO).collect(Collectors.toList());
            log.info("현재 페이지는 + " + String.valueOf(pageNum));
            log.info(kidDTOS.toString());
            log.info(nowKidsFileDTOS.toString());
            JSONObject jsonObject = new JSONObject(nowKidsDTO);
            jsonObject.put("kids", kidDTOS);
            jsonObject.put("files", nowKidsFileDTOS);

            log.info(jsonObject.toString());

            jsonArray.put(jsonObject);
        });

        model.addAttribute("nowKidsDTOS", jsonArray.toString());
        return "/nowKids/now-kids-list";
    }


    public JSONObject convertTupleToJson(Tuple tuple) {
        JSONObject json = new JSONObject();

        json.put("event", tuple.get(0, Event.class));
        json.put("calendar", tuple.get(1, Calendar.class));
        // 필요한 경우 다른 필드도 JSON에 추가

        return json;
    }
}
