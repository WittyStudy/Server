package witty.studyapp.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import witty.studyapp.execption.custom.NotLoginMemberException;
import witty.studyapp.execption.result.ErrorResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import static witty.studyapp.constant.exception.ExceptionConstant.NOT_FOUND_LOGIN;
import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;
import static witty.studyapp.constant.session.SessionConstant.LOGIN_UUID;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestURI = request.getRequestURI();
        String uuid = String.valueOf(UUID.randomUUID());
        request.setAttribute(LOGIN_UUID, uuid);
        log.info("preHandle, UUID=[{}]", uuid);
        log.info("preHandle, requestURI=[{}]", requestURI);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
            log.warn("===No session valid===");
            log.warn("request/Locale=[{}]", request.getLocale());
            log.warn("request/RemoteHost=[{}]", request.getRemoteHost());
            log.warn("request/Method=[{}]", request.getMethod());

            Enumeration<String> parameterNames = request.getParameterNames();
            while(parameterNames.hasMoreElements()){
                log.warn("request/parameter=[{}]",request.getParameter(parameterNames.nextElement()));
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            objectMapper.writeValue(response.getWriter(), new ErrorResult(new NotLoginMemberException(), NOT_FOUND_LOGIN));
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("afterCompletion, UUID=[{}]", request.getAttribute(LOGIN_UUID));
    }
}
