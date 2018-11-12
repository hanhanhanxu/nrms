package cn.itcast.service;

import cn.itcast.mapper.AdminMapper;
import cn.itcast.pojo.Admin;
import cn.itcast.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void addAdmin(Admin admin) {
        admin.setAdmin_id(WebUtils.generateID());//随机设置id
        adminMapper.insert(admin);
    }

    @Override
    public void delAdmin(Admin admin) {
        adminMapper.delete(admin);
    }

    @Override
    public Admin modifyAdmin(Admin admin) {

        return null;
    }

    @Override
    public Admin Verification(Admin admin) {//登录时校验，账号密码是否正确
        return adminMapper.selectByUsernamePassword(admin);
    }

    @Override
    public String searchAdminByUsername(String username) {//注册时校验，登录账号是否存在
        return adminMapper.selectByUsername(username);
    }
}
