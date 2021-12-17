package witty.studyapp.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import witty.studyapp.annotation.Login;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.custom.NotLoginMemberException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;

public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new NotLoginMemberException();
        }
        return session.getAttribute(LOGIN_MEMBER);
    }
}
