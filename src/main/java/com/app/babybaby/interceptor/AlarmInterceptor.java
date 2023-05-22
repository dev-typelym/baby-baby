package com.app.babybaby.interceptor;

import com.app.babybaby.repository.alert.AlertRepository;
import com.app.babybaby.type.AlertReadStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmInterceptor implements HandlerInterceptor {

    private AlertRepository alertRepository;
//    private MyAlarmRestController alarmRestController;
//    private GroupRepository groupRepository;
//    private QuestAchievementRepositoryImpl questAchievementRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.getSession().setAttribute("noReadAlarm",alertRepository.getNoReadAlertList());
//        alertRepository.findAllByMemberId(세션에 알람 갯수 담아놓기);
//        헤더로 가서 세션에서 방금 저장한 알림 갯수를 가져와서 뿌리되 내가 원하는 상태를 헤더에서 검사한다. 안 읽은 알림이 없다면 빨간 점을 없애고 그냥 종 모양이 보이게 한다.
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
