package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.service.board.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("event/*")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final EventService eventService;

    
//    /*test 용 컨트롤러*/
//    @ResponseBody
//    @GetMapping("lists")
//    public Slice<Event> getEventList(@RequestParam(required = false) EventBoardSearch eventBoardSearch,
//                                     @PageableDefault(size = 10) Pageable pageable) {
//        return eventService.findEventListWithPaging(eventBoardSearch, pageable);
//    }

    /*test 용 */
    @GetMapping("list")
    public String getList(@RequestParam(required = false) EventBoardSearch eventBoardSearch,
                                   @PageableDefault(size = 10) Pageable pageable, Model model){
        model.addAttribute("eventList",eventService.findEventListWithPaging(eventBoardSearch, PageRequest.of(0,10)));
        return "play/event-category-list";
    }
//
//    /*test 용 */
//    @ResponseBody
//    @GetMapping("list")
//    public Slice<EventDTO> getList(@RequestParam(required = false) EventBoardSearch eventBoardSearch,
//                                   @PageableDefault(size = 10) Pageable pageable, Model model){
//        model.addAttribute("eventList",eventService.findEventListWithPaging(eventBoardSearch, PageRequest.of(0,10)));
//        return eventService.findEventListWithPaging(eventBoardSearch, pageable);
//    }


}
