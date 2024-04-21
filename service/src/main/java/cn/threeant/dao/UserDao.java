package cn.threeant.dao;

import cn.threeant.model.UserAccess;

import java.io.IOException;
import java.util.List;

public interface UserDao {

    void addUser(UserAccess request) throws IOException;
    UserAccess getUser(long userId);

    List<UserAccess> allUser();

    void updateUser(UserAccess request);
}
