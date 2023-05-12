package com.app.babybaby.controller.boardController;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.service.board.nowKidsService.NowKids;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/nowKid/*")
public class NowKidsController {
    private final NowKids nowKids;

    @GetMapping("write")
    public String goWriteNowKids(Long sessionId, Model model, RedirectAttributes redirectAttributes) {
        sessionId = 1L;
//        Page<NowKidsDTO> nowKidsDTOS = nowKidsServices.getAllInfoForListDesc_QueryDsl(1, 30, sessionId);
//
//        JSONArray jsonArray = new JSONArray();
//        nowKidsDTOS.forEach(dto -> {
//            JSONObject json = new JSONObject(dto);
//            try {
//                json.put("nowKidsId", dto.getNowKidsId());
//                json.put("eventId", dto.getEventId());
//                json.put("boardTitle", dto.getBoardTitle());
//                json.put("eventAddress", dto.getEventLocation().getAddress());
//                json.put("eventAddressDetail", dto.getEventLocation().getAddressDetail());
//                json.put("eventAddressSubDetail", dto.getEventLocation().getAddressSubDetail());
//                json.put("eventAddressSubDetail", dto.getEventLocation().getAddressSubDetail());
//                json.put("eventPostCode", dto.getEventLocation().getPostcode());
//                json.put("category", dto.getCategory());
//                json.put("startDate", dto.getCalendar().getStartDate());
//                json.put("endDate", dto.getCalendar().getEndDate());
//                json.put("eventUploadTIme", dto.getEventUploadTIme());
//                json.put("eventUpdateTime", dto.getEventUpdateTime());
//                json.put("memberId", dto.getMemberId());
//                json.put("memberNickname", dto.getMemberNickname());
//                json.put("memberProfileOriginalName", dto.getMemberProfileOriginalName());
//                json.put("memberProfileUUID", dto.getMemberProfileUUID());
//                json.put("memberProfilePath", dto.getMemberProfilePath());
//                json.put("memberRegisterDate", dto.getMemberRegisterDate());
//                json.put("memberType", dto.getMemberType());
//                json.put("memberGuideStatus", dto.getMemberGuideStatus());
//                json.put("memberSleep", dto.getMemberSleep());
//                json.put("memberGuideType", dto.getMemberGuideType());
//                json.put("uploadTime", dto.getUploadTime());
//                json.put("kids", dto.getKids());
//            } catch (Exception e){
//                return;
//            }
//            jsonArray.put(json);
//        });
//
//        log.info(jsonArray.toString());
//
//        model.addAttribute("nowKidsDTOS", jsonArray.toString());


        log.info(nowKids.getBoardAndCalendarByGeneralGuideId(sessionId).toString());
        List<Event> events = nowKids.getBoardAndCalendarByGeneralGuideId(sessionId);
        JSONArray jsonArray = new JSONArray();
        events.forEach(event -> {
            JSONObject json = new JSONObject(event);
            jsonArray.put(json);
        });
        model.addAttribute("events", jsonArray.toString());

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
    public String goNowKidsList(){
        return "/nowKids/now-kids-list";
    }
}
