package cn.itcast.service;

import cn.itcast.pojo.Admin;

public interface AdminService {
    public void addAdmin(Admin admin);//注册
    public void delAdmin(Admin admin);//删除
    public Admin modifyAdmin(Admin admin);//
    public Admin Verification(Admin admin);//登录时验证，返回账号的全部信息
    public String searchAdminByUsername(String username);//注册时验证，是否有重复的登录账号
}
