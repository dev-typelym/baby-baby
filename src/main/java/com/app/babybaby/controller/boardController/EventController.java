package com.app.babybaby.controller.boardController;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.eventDTO.PageRequestDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/event/*")
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

    @GetMapping("lists")
    public Slice<EventDTO> getList(/*@RequestParam(required = false) EventBoardSearch eventBoardSearch,*/ @PageableDefault(size = 10) Pageable pageable, Model model){
        Slice<EventDTO> eventListDTO = eventService.findEventListWithPaging(/*eventBoardSearch,*/ PageRequest.of(0,10));
        return eventListDTO;
    }

    @GetMapping("list")
    public String getList(Model model/*,@RequestParam(required = false) EventBoardSearch eventBoardSearch*/, @PageableDefault(size = 10) Pageable pageable){
        model.addAttribute("eventList",eventService.findEventListWithPaging(/*eventBoardSearch,*/ PageRequest.of(0,10)));
        return "play/event-category-list";
    }


    @ResponseBody
    @PostMapping("list")
    public Slice<EventDTO> getEvents(/*@RequestParam(required = false) EventBoardSearch eventBoardSearch,*/@RequestBody PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage(), 8);
        Slice<EventDTO> eventListDTO = eventService.findEventListWithPaging(/*eventBoardSearch,*/pageable);
        log.info(eventListDTO.getContent()+"");
        log.info("들어왓니 ?");
        log.info(pageable.toString());

        return eventListDTO;
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
