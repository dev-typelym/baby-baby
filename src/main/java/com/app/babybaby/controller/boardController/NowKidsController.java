package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.service.board.nowKids.NowKidsService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/nowKid/*")
public class NowKidsController {
    private final NowKidsService nowKidsService;
    private final NowKidsRepository nowKidsRepository;

    @GetMapping("write")
    public String goWriteNowKids(Long sessionId, Model model, RedirectAttributes redirectAttributes) {
        sessionId = 1L;
        List<Tuple> nowKidsEvents = nowKidsRepository.findEventAndCalendarInfoByGuideId_QueryDsl(sessionId);
        JSONArray calendars = new JSONArray();
        JSONArray events = new JSONArray();

        for (Tuple tuple : nowKidsEvents) {
            Event event = tuple.get(0, Event.class);
            Calendar calendar = tuple.get(1, Calendar.class);


            JSONObject eventJSON = new JSONObject();
            eventJSON.put("boardTitle", event.getBoardTitle());
            log.info(event.toString());
            log.info(calendar.toString());

            JSONObject calendarJSON = new JSONObject(calendar);
            events.put(eventJSON);
            calendars.put(calendarJSON);
        }
//        log.info(events.toString());
//        log.info(calendars.toString());
        log.info("eventTITLE가져오기 : " + events);
        log.info("======================================================================");
        model.addAttribute("calendars", calendars.toString());
        model.addAttribute("eventTitle", events.toString());
        return "/nowKids/now-kids-write";
    }

    @PostMapping("event")
    @ResponseBody
    public List<Kid> getAllKids(Long sessionId, Long eventId){
        return null;
    }





    @GetMapping("multi")
    public String writeNowKidFiles(){
        return "/nowKids/now-kids-write-multi";
    }





    @GetMapping("list")
    public String goNowKidsList(Long sessionId, Model model){

        sessionId = 1L;
        Page<NowKidsDTO> nowKidsDTOS = nowKidsService.getAllInfoForListDesc(1, 5);
        JSONArray jsonArray = new JSONArray();
        nowKidsDTOS.forEach(nowKidsDTO -> {
            List<Kid> kids = nowKidsRepository.findAllKidsByEventIdAndGuideId_QueryDsl(nowKidsDTO.getMemberId(), nowKidsDTO.getEventId());
            List<NowKidsFile> nowKidsFiles = nowKidsRepository.findAllFileNowKidsById_QueryDsl(nowKidsDTO.getNowKidsId());

            JSONObject jsonObject = new JSONObject(nowKidsDTO);
            jsonObject.put("kids", kids);
            jsonObject.put("files", nowKidsFiles);

            jsonArray.put(jsonObject);
        });

        log.info(jsonArray.toString());
        model.addAttribute("nowKidsDTOS", nowKidsDTOS);
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
