package com.app.babybaby.interceptor;

import com.app.babybaby.repository.alert.AlertRepository;
import com.app.babybaby.repository.alert.alertFollow.AlertFollowRepository;
import com.app.babybaby.type.AlertReadStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmInterceptor implements HandlerInterceptor {

    private final AlertRepository alertRepository;
    private final AlertFollowRepository alertFollowRepository;
//    private MyAlarmRestController alarmRestController;
//    private GroupRepository groupRepository;
//    private QuestAchievementRepositoryImpl questAchievementRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("noReadAlarm", alertFollowRepository.getNoReadAlert());
//        Long NoReadCount = (Long)request.getAttribute("noReadAlarm");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

//    private boolean isRedirectView(ModelAndView modelAndView) {
//        return modelAndView.getViewName().startsWith("redirect:")
//                || modelAndView.getView() instanceof RedirectView;
//    }
}
