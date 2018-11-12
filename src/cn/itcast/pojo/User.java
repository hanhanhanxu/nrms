package cn.itcast.pojo;

public class User {
    private String User_id;
    private String nickname;
    private String username;
    private String password;
    private String pobelong;
    private String tel;
    private Integer constraint;

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

    public User(String user_id, String nickname, String username, String password, String pobelong, String tel, Integer constraint) {
        User_id = user_id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.pobelong = pobelong;
        this.tel = tel;
        this.constraint = constraint;
    }

    public User() {

    }
}
