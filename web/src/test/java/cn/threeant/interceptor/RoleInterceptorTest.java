package cn.threeant.interceptor;

import cn.threeant.annotation.CheckRole;
import cn.threeant.model.AuthHeader;
import cn.threeant.model.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class RoleInterceptorTest {

    private RoleInterceptor interceptor;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerMethod handlerMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        interceptor = new RoleInterceptor();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handlerMethod = mock(HandlerMethod.class);

    }

    @Test
    void testCorrectRoleAccess() throws Exception {
        AuthHeader authHeader = new AuthHeader(123456L, "XXXXXXX", Role.admin);
        ObjectMapper objectMapper = new ObjectMapper();
        String encodedHeader = Base64.getEncoder().encodeToString(
                objectMapper.writeValueAsString(authHeader).getBytes()
        );
        request.addHeader("Authorization", encodedHeader);
        CheckRole checkRole = mock(CheckRole.class);
        when(handlerMethod.getMethodAnnotation(CheckRole.class)).thenReturn(checkRole);
        when(checkRole.value()).thenReturn(new Role[]{Role.admin});
        assertTrue(interceptor.preHandle(request, response, handlerMethod));
    }

    @Test
    void testIncorrectRoleAccess() throws Exception {
        AuthHeader authHeader = new AuthHeader(123456L, "XXXXXXX", Role.user);
        ObjectMapper objectMapper = new ObjectMapper();
        String encodedHeader = Base64.getEncoder().encodeToString(
                objectMapper.writeValueAsString(authHeader).getBytes()
        );
        request.addHeader("Authorization", encodedHeader);
        CheckRole checkRole = mock(CheckRole.class);
        when(handlerMethod.getMethodAnnotation(CheckRole.class)).thenReturn(checkRole);
        when(checkRole.value()).thenReturn(new Role[]{Role.admin});
        assertFalse(interceptor.preHandle(request, response, handlerMethod));
        assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
        assertEquals("Access Denied", response.getContentAsString());
    }

    @Test
    void testNoAuthorizationHeader() throws Exception {
        assertTrue(interceptor.preHandle(request, response, handlerMethod));
    }

    @Test
    void testNonHandlerMethodAccess() throws Exception {
        Object handler = new Object();
        assertTrue(interceptor.preHandle(request, response, handler));
    }


}