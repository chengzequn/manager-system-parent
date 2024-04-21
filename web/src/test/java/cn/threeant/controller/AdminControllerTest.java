package cn.threeant.controller;

import cn.threeant.model.UserAccess;
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
class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private UserService userService;

    @Test
    void addUser() {
        UserAccess request=new UserAccess();
        Mockito.doNothing().when(userService).addUser(request);
        ResponseWrapper responseWrapper = adminController.addUser(request);
        Assertions.assertEquals(200,responseWrapper.getStatus());
    }
}