package cn.itcast.vo;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String User_id;
    private String nickname;
    private String username;
    private String password;
    private String password2;
    private String pobelong;
    private String tel;
    private Integer constraint;

    private Map<String,String> errors = new HashMap<String,String>();

    public boolean invadate2(){
        boolean isOK = true;
        if("".equals(username) || username==null){
            isOK = false;
            errors.put("username","登录账号不能为空！");
        }
        if("".equals(password) || password==null){
            isOK = false;
            errors.put("password","登录密码不能为空！");
        }
        return isOK;
    }

    public boolean invadate1(){
        boolean isOK = true;

        if(nickname==null || nickname.trim().equals("")){
            isOK = false;
            errors.put("nickname","昵称不能位空！");
        }

        if(username==null || username.trim().equals("")) {
            isOK = false;
            errors.put("username", "登录账号不能为空！");
        }else {
            if(!this.username.matches("\\d{6,11}")) {//登录账号必须为6-11位数字
                isOK = false;
                errors.put("username", "登录账号必须为6-11位数字！");
            }
        }

        if(password==null || password.trim().equals("")) {
            isOK = false;
            errors.put("password", "登录密码不能为空！");
        }else {
            if(!this.password.matches("[A-Za-z0-9]{6,11}")) {//登录密码必须为6-11位数字或字母或其组合
                isOK = false;
                errors.put("password", "登录密码必须为6-11位数字或字母或其组合,不能包含其他符号！");
            }
        }

        if(password2==null || password2.trim().equals("")) {
            isOK = false;
            errors.put("password2", "确认密码不能为空！");
        }else {
            if(!password2.equals(password)) {
                isOK = false;
                errors.put("password2", "两次输入密码不一致！");
            }
        }

        if("".equals(pobelong)){
            isOK = false;
            errors.put("pobelong","归属地不能为空！");
        }

        if("".equals(tel)){
            isOK = false;
            errors.put("tel","电话不能为空！");
        }

        return isOK;
    }


    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPobelong() {
        return pobelong;
    }

    public void setPobelong(String pobelong) {
        this.pobelong = pobelong;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getConstraint() {
        return constraint;
    }

    public void setConstraint(Integer constraint) {
        this.constraint = constraint;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
