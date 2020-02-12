package top.simplelife42.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.simplelife42.community.mapper.UserMapper;
import top.simplelife42.community.model.User;
import top.simplelife42.community.model.UserExample;

import java.util.List;

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

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setBio(user.getBio());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            //先取到目标example，然后生成一个更新后的updateUser，属性不为空的才更新
            userMapper.updateByExampleSelective(updateUser, example);
        }

    }
}
