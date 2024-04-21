package cn.threeant.controller;

import cn.threeant.annotation.CheckRole;
import cn.threeant.model.UserAccess;
import cn.threeant.model.Role;
import cn.threeant.service.UserService;
import cn.threeant.vo.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/addUser")
    @CheckRole({Role.admin})
    public ResponseWrapper addUser(@RequestBody UserAccess request) {
        userService.addUser(request);
        return ResponseWrapper.ok();
    }


}
