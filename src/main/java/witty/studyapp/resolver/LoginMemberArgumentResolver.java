package witty.studyapp.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.execption.NotLoginMemberException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;

public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = MemberLoginDTO.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new NotLoginMemberException("먼저 로그인을 해야 합니다.");
        }
        return session.getAttribute(LOGIN_MEMBER);
    }
}
