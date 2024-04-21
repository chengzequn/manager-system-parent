package cn.threeant.service;

import cn.threeant.model.UserAccess;
import cn.threeant.model.AuthHeader;
import cn.threeant.model.Role;
import cn.threeant.vo.ResponseWrapper;

import java.util.List;

public interface UserService {

    AuthHeader decodeAuthHeader(String header);

    boolean checkRole(AuthHeader authHeader, Role role);

    void addUser(UserAccess request);

    ResponseWrapper getEndpoints(long userId,String recource);

    ResponseWrapper hasAccess(List<String> endpoints, String resource);
}
