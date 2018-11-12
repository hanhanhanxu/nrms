package cn.itcast.service;

import cn.itcast.pojo.User;

public interface UserService {
    public void addUser(User admin);//注册
    public void delUser(User admin);
    public User modifyUser(User admin);
    public User Verification(User admin);//登录时验证，返回账号的全部信息
    public String searchUserByUsername(String username);//注册时验证，是否有重复的登录账号
}
