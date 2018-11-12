package cn.itcast.service;

import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.User;
import cn.itcast.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        user.setUser_id(WebUtils.generateID());//随即设置ID
        userMapper.insert(user);
    }

    @Override
    public void delUser(User user) {
        userMapper.delete(user);
    }

    @Override
    public User modifyUser(User user) {
        return null;
    }

    @Override
    public User Verification(User user) {//登录时校验，账号密码是否正确
        return userMapper.selectByUsernamePassword(user);
    }

    @Override
    public String searchUserByUsername(String username) {//注册时校验，登录账号是否存在
        return userMapper.selectByUsername(username);
    }
}
