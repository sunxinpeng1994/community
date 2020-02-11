package top.simplelife42.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.simplelife42.community.mapper.UserMapper;
import top.simplelife42.community.model.User;

/**
 * @Desc
 * @Author Xinpeng Sun
 * @CreateTime 2/10/2020 5:23 PM
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId((user.getAccountId()));
        if(dbUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setBio(user.getBio());
            userMapper.update(dbUser);
        }

    }
}
