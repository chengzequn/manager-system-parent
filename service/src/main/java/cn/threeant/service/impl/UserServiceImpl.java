package cn.threeant.service.impl;

import cn.threeant.dao.UserDao;
import cn.threeant.exception.BusinessException;
import cn.threeant.model.UserAccess;
import cn.threeant.model.AuthHeader;
import cn.threeant.model.Role;
import cn.threeant.service.UserService;
import cn.threeant.vo.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public AuthHeader decodeAuthHeader(String header) {
        try {
            String decodeJson = new String(Base64.getDecoder().decode(header));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(decodeJson, AuthHeader.class);
        } catch (JsonProcessingException e) {
            throw new BusinessException("decode auth header fail");
        }
    }

    @Override
    public boolean checkRole(AuthHeader authHeader, Role role) {
        return authHeader.getRole().equals(role);
    }

    @Override
    public void addUser(UserAccess request) {
        try {
            userDao.addUser(request);
        }catch (IOException e){
            throw new BusinessException("add user fail");
        }
    }

    @Override
    public ResponseWrapper getEndpoints(long userId,String recource) {
        UserAccess userAccess = userDao.allUser().stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
        if(userAccess == null || userAccess.getEndpoint() == null){
            return ResponseWrapper.error(403,"没有权限");
        }
        return hasAccess(userAccess.getEndpoint(), recource);
    }


    @Override
    public ResponseWrapper hasAccess(List<String> endpoints, String resource) {
        if(endpoints.contains(resource)){
            return ResponseWrapper.ok();
        }
        return ResponseWrapper.error(403,"没有权限");
    }


}
