package cn.threeant.controller;

import cn.threeant.model.AuthHeader;
import cn.threeant.service.UserService;
import cn.threeant.vo.ResponseWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @Mock
    AuthHeader auth;

    @Test
    void getResource() {
        Mockito.when(userService.decodeAuthHeader(Mockito.anyString())).thenReturn(auth);
        Mockito.when(auth.getUserId()).thenReturn(123456L);
        ResponseWrapper responseWrapper = new ResponseWrapper(200,"success");
        Mockito.when(userService.getEndpoints(Mockito.anyLong(),Mockito.anyString())).thenReturn(responseWrapper);
        ResponseWrapper resource = controller.getResource("test", "test");
        Assertions.assertEquals("success",resource.getMessage());
    }

}