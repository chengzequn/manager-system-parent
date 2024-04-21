package cn.threeant.dao;

import cn.threeant.exception.BusinessException;
import cn.threeant.model.UserAccess;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FileUserDao implements  UserDao{

    @Value("${manager-system.data-file-path}")
    private String dataFilePath;

    private String userFile = "user.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void addUser(UserAccess request) throws IOException {
        List<UserAccess> list = allUser();
        Optional<UserAccess> optional = list.stream().filter(user -> user.getUserId().equals(request.getUserId())).findFirst();
        if(optional.isPresent()){
            optional.get().setEndpoint(request.getEndpoint());
        }else{
            list.add(request);
        }
        writeToFile(list);
    }

    @Override
    public UserAccess getUser(long userId) {
        return allUser().stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public List<UserAccess> allUser() {
        try {
            File file = new File(dataFilePath+userFile);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<UserAccess>>() {
                });
            }

        }catch(IOException e){
            throw new BusinessException("get all user fail");
        }

        return new ArrayList<>();
    }

    @Override
    public void updateUser(UserAccess request) {
        try {
            List<UserAccess> list = allUser();
            list.forEach(user -> {
                if (user.getUserId().equals(request.getUserId())) {
                    user.setEndpoint(request.getEndpoint());
                }
            });
            writeToFile(list);
        }catch (IOException e){
            throw new BusinessException("update user fail");
        }
    }

    private void writeToFile(List<UserAccess> list) throws IOException {
        objectMapper.writeValue(new File(dataFilePath+userFile),list);
    }
}
