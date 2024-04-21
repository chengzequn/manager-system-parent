package cn.threeant.interceptor;

import cn.threeant.annotation.CheckRole;
import cn.threeant.model.AuthHeader;
import cn.threeant.model.Role;
import cn.threeant.util.Base64Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RoleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            CheckRole roleRequired = handlerMethod.getMethodAnnotation(CheckRole.class);
            if (roleRequired != null) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader != null) {
                    String decodedHeader = new String(Base64Util.decode(authHeader));
                    ObjectMapper objectMapper = new ObjectMapper();
                    AuthHeader auth = objectMapper.readValue(decodedHeader, AuthHeader.class);
                    Role role = auth.getRole();
                    for (Role allowedRole : roleRequired.value()) {
                        if (allowedRole.name().equals(role.toString())) {
                            return true;
                        }
                    }
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Access Denied");
                    return false;
                }
            }
        }
        return true;
    }

}
