package com.app.babybaby.controller.boardController;


import com.app.babybaby.domain.adminDTO.AdminAnnouncementDTO;
import com.app.babybaby.search.admin.AdminAnnouncementSearch;
import com.app.babybaby.service.admin.adminAnnouncement.AdminAnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-part/*")
@RequiredArgsConstructor
@Slf4j
public class AnnouncementController {

    @GetMapping("announcement")
    public void announcement(){}

    private final AdminAnnouncementService adminAnnouncementService;

    @GetMapping("announcement-list/{page}")
    @ResponseBody
    public Page<AdminAnnouncementDTO> getAnnouncement(@PathVariable("page") int page, AdminAnnouncementSearch adminAnnouncementSearch){
        Page<AdminAnnouncementDTO> adminAnnouncements = adminAnnouncementService.getAdminAnnouncementListWithPaging(page -1, adminAnnouncementSearch);
        return adminAnnouncements;
    }
}
