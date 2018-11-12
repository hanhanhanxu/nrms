package cn.itcast.mapper;

import cn.itcast.pojo.User;

public interface UserMapper {
    int insert(User user);//完全插入，所有字段
    int delete(User user);
    User selectByUsernamePassword(User user);//登录账号时，通过账号密码查询，返回此账户所有信息
    String selectByUsername(String username);//注册账号时，检查此登录账号是否重复
}
