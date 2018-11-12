package cn.itcast.controller;

import cn.itcast.service.ElefeeService;
import cn.itcast.service.UserService;
import cn.itcast.vo.Admin;
import cn.itcast.service.AdminService;
import cn.itcast.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/LoginAndRegist")
public class LoginAndRegist {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private ElefeeService elefeeService;

    @RequestMapping("/registAdmin")
    public String registAdmin(Admin admin_vo, HttpSession session, HttpServletRequest request) {
        //检查是否为空，两个密码是否一致
        if (false == admin_vo.invadate1()) {
            request.setAttribute("form", admin_vo);
            return "/WEB-INF/jsp/regist.jsp";
        }
        cn.itcast.pojo.Admin admin = new cn.itcast.pojo.Admin(admin_vo.getAdmin_id(), admin_vo.getNickname(), admin_vo.getUsername(), admin_vo.getPassword(), admin_vo.getPobelong(), admin_vo.getTel(), 1);
        if (null == adminService.searchAdminByUsername(admin.getUsername())) {
            adminService.addAdmin(admin);
            session.setAttribute("message", "注册成功，请登录。");
            return "redirect:/index.jsp";
        } else {//登录账号重复
            admin_vo.getErrors().put("username", "登录账号已存在！");
            request.setAttribute("form", admin_vo);
            return "/WEB-INF/jsp/regist.jsp";
        }
    }

    @RequestMapping("/registUser")
    public String registUser(User user_vo, HttpSession session, HttpServletRequest request) {
        //检查是否为空，两个密码是否一致
        if (false == user_vo.invadate1()) {
            request.setAttribute("form", user_vo);
            return "/WEB-INF/jsp/regist.jsp";
        }
        cn.itcast.pojo.User user = new cn.itcast.pojo.User(user_vo.getUser_id(), user_vo.getNickname(), user_vo.getUsername(), user_vo.getPassword(), user_vo.getPobelong(), user_vo.getTel(), 2);
        if (null == userService.searchUserByUsername(user.getUsername())) {
            userService.addUser(user);
            session.setAttribute("message", "注册成功，请登录。");
            return "redirect:/index.jsp";
        } else {//登录账号重复
            user_vo.getErrors().put("username", "登录账号已存在！");
            request.setAttribute("form", user_vo);
            return "/WEB-INF/jsp/regist.jsp";
        }
    }

    @RequestMapping("/login")
    public String login(Admin admin_vo, String identity, HttpSession session, HttpServletRequest request) {
        //如果是账户信息为空，则直接返回
        if (false == admin_vo.invadate2()) {
            request.setAttribute("form", admin_vo);
            return "/index.jsp";
        }
        //账号密码都不为空，就开始看是用户还是管理员，再从相关表中查找
        //判断是用户还是管理员
        if ("2".equals(identity.trim())) {//用户
            //包装一个账户
            cn.itcast.pojo.User user = new cn.itcast.pojo.User();
            user.setUsername(admin_vo.getUsername());
            user.setPassword(admin_vo.getPassword());
            //拥有此账户所有信息的对象adminAllInfo
            cn.itcast.pojo.User userAllInfo = new cn.itcast.pojo.User();
            //校验能否登录，能则返回此账户的所有信息
            userAllInfo = userService.Verification(user);
            if (userAllInfo != null) {//从数据库中查询出此账号密码
                //将信息存入session域中
                session.setAttribute("user", userAllInfo);
                //loadMenuUser(session);//加载电费表中的信息
                //return "/WEB-INF/jsp/menu.jsp";//登录，进入用户菜单页面
                //return "/a.jsp";
                return "/UI/tomenu.action";
            } else {
                request.setAttribute("message", "账号或密码错误！");
                return "/index.jsp";
            }
        } else if ("1".equals(identity.trim())) {
            //包装一个账户
            cn.itcast.pojo.Admin admin = new cn.itcast.pojo.Admin();
            admin.setUsername(admin_vo.getUsername());
            admin.setPassword(admin_vo.getPassword());
            //拥有此账户所有信息的对象adminAllInfo
            cn.itcast.pojo.Admin adminAllInfo = new cn.itcast.pojo.Admin();
            //校验能否登录，从数据库中查询出此账号密码
            adminAllInfo = adminService.Verification(admin);
            if (adminAllInfo != null) {
                //将信息存入session域中
                session.setAttribute("admin", adminAllInfo);
                return "/WEB-INF/jsp/menuAdmin.jsp";//登录，进入管理员菜单页面
            } else {
                request.setAttribute("message", "账号或密码错误！");
                return "/index.jsp";
            }
        } else {
            request.setAttribute("error", "一个不可预估的问题引起了错误，请及时向开发者反馈...");
            return "/error.jsp";
        }
    }

    /*private void loadMenuUser(HttpSession session) {

    }*/


}
