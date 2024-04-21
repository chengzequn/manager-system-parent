package cn.threeant.controller;

import cn.threeant.annotation.CheckRole;
import cn.threeant.model.AuthHeader;
import cn.threeant.model.Role;
import cn.threeant.service.UserService;
import cn.threeant.vo.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @CheckRole({Role.admin,Role.user})
    @GetMapping("/{resource}")
    public ResponseWrapper getResource(@RequestHeader(value = "Authorization") String authHeader,@PathVariable("resource") String resource)
    {
        AuthHeader auth = userService.decodeAuthHeader(authHeader);
        long userId=auth.getUserId();
        return userService.getEndpoints(userId,resource);
    }

}
