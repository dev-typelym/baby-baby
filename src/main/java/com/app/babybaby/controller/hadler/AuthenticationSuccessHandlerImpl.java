package com.app.babybaby.controller.hadler;

import com.app.babybaby.controller.provider.UserDetail;
import com.app.babybaby.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private static final String REDIRECT_URL_FOR_GENERAL = "/main/main";
    private static final String REDIRECT_URL_FOR_COMPANY = "/main/main";
    private static final String REDIRECT_URL_FOR_ADMIN = "/admin/admin-memberList";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(((UserDetail)authentication.getPrincipal()).getMemberRole().equals(Role.ADMIN)) {
            log.info("ADMIN_SUCCESS");
            response.sendRedirect(REDIRECT_URL_FOR_ADMIN);
        }else if(((UserDetail)authentication.getPrincipal()).getMemberRole().equals(Role.COMPANY)){
            log.info("COMPANY_SUCCESS");
            response.sendRedirect(REDIRECT_URL_FOR_COMPANY);
        }else {
            log.info("GENERAL_SUCCESS");
            response.sendRedirect(REDIRECT_URL_FOR_GENERAL);
        }
    }

}
