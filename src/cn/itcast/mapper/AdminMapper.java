package cn.itcast.mapper;


import cn.itcast.pojo.Admin;

public interface AdminMapper {
    int insert(Admin admin);//完全插入，所有字段
    int delete(Admin admin);
    Admin selectByUsernamePassword(Admin admin);//登录账号时，通过账号密码查询，返回此账户所有信息
    String selectByUsername(String username);//注册账号时，检查此登录账号是否重复
}
