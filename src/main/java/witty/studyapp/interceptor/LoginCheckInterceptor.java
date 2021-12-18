package witty.studyapp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import witty.studyapp.execption.custom.NotLoginMemberException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.UUID;

import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;
import static witty.studyapp.constant.session.SessionConstant.LOGIN_UUID;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String uuid = String.valueOf(UUID.randomUUID());
        request.setAttribute(LOGIN_UUID, uuid);
        log.info("preHandle, UUID=[{}]", uuid);
        log.info("preHandle, requestURI={}", requestURI);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
            log.warn("No session valid.");
            log.warn("requestLocale={}",request.getLocale());
            log.warn("requestRemoteHost={}",request.getRemoteHost());

            throw new NotLoginMemberException();
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String uuid = (String) request.getAttribute(LOGIN_UUID);
        if (uuid != null)
            log.info("afterCompletion, UUID=[{}]", uuid);

    }
}
