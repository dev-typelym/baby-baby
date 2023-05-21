package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTOForParameter;
import com.app.babybaby.domain.memberDTO.KidDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
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

    @GetMapping("writeFirst")
    public String goWriteNowKids(Model model, RedirectAttributes redirectAttributes) {
        Long sessionId = 2L;
        Member member = memberRepository.findById(sessionId).get();
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
        model.addAttribute("memberNickname", member.getMemberNickname());

        return "nowKids/now-kids-write";
    }





    @PostMapping("getKids")
    @ResponseBody
    public String getAllKids(Long eventId) {
        Long sessionId = 2L;
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





    @GetMapping("writeSecond")
    public String writeNowKidFiles(Long eventId, String eventDate, Model model){
        Long sessionId = 2L;
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


    @PostMapping("save")
    public RedirectView saveAllNowKids(Long eventId, String eventDate, NowKidsFileDTOForParameter nowKidsFileDTOForParameter){
        Long sessionId = 2L;
        log.info("eventDate는 : " + eventDate);
        log.info("EventId는 : " + eventId.toString());
        log.info("save에서의 nowKidsFileDTO는 : " + nowKidsFileDTOForParameter.toString());
        log.info("Save입니다----------------------------------------------------------------------------");

        Event event = nowKidsFileService.getBoardInfoByEventId(eventId);
        Member member = memberRepository.findById(sessionId).get();
        NowKids nowKids = new NowKids(event, member);
        NowKids savedNowKids = nowKidsRepository.save(nowKids);
        nowKidsFileService.saveAllFiles(nowKidsFileDTOForParameter.getFiles(), savedNowKids.getId());
//        nowKidsDTO.getFiles().stream().map(NowKidsFileDTO::toString).forEach(log::info);

        return new RedirectView("/nowKid/list");
    }



    @GetMapping("list")
    public String goNowKidsList( Model model){
        /* 8명 위에 맴버 가져오기 */
        List<MemberDTO> memberDTOS = nowKidsService.find8AuthorDesc();
        log.info("내가 가져온 맴버들 : " + memberDTOS);
        model.addAttribute("memberDTOS", memberDTOS);
        return "/nowKids/now-kids-list";
    }

    /* 최신순 프로필 가져오기*/
    @PostMapping("getList")
    @ResponseBody
    public String getList(Integer pageNumber){
        Long sessionId = 2L;
        Page<NowKidsDTO> nowKidsDTOS = nowKidsService.getAllInfoForListDesc(pageNumber, 2, sessionId);
//        페이지에 아무것도 없다면 빈 배열을 리턴
        if(nowKidsDTOS.isEmpty()){
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        /* 모든 게시글 */
        nowKidsDTOS.forEach(nowKidsDTO -> {
            List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(nowKidsDTO.getMemberId(), nowKidsDTO.getEventId());
            List<NowKidsFile> nowKidsFiles = nowKidsRepository.findAllFileNowKidsById_QueryDsl(nowKidsDTO.getNowKidsId());
            List<KidDTO> kidDTOS = kids.stream().map(kidService::toKidDTO).collect(Collectors.toList());
            List<NowKidsFileDTO> nowKidsFileDTOS = nowKidsFiles.stream().map(nowKidsFileService::toNowKidsFileDTO).collect(Collectors.toList());
            JSONObject jsonObject = new JSONObject(nowKidsDTO);
//            jsonObject.put("kids", kidDTOS);
//            jsonObject.put("files", nowKidsFileDTOS);
            jsonObject.put("files", new JSONArray(nowKidsFileDTOS));
            jsonObject.put("kids", new JSONArray(kidDTOS));

            jsonArray.put(jsonObject);
        });
        log.info(String.valueOf(jsonArray));
        return jsonArray.toString();
    }


    public JSONObject convertTupleToJson(Tuple tuple) {
        JSONObject json = new JSONObject();

        json.put("event", tuple.get(0, Event.class));
        json.put("calendar", tuple.get(1, Calendar.class));
        // 필요한 경우 다른 필드도 JSON에 추가

        return json;
    }
}
