package cn.itcast.pojo;

public class Admin {

    private String Admin_id;
    private String nickname;
    private String username;
    private String password;
    private String pobelong;
    private String tel;
    private Integer constraint;

    public Admin() {

    }

    public Admin(String admin_id, String nickname, String username, String password, String pobelong, String tel, Integer constraint) {
        Admin_id = admin_id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.pobelong = pobelong;
        this.tel = tel;
        this.constraint = constraint;
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

    public String getAdmin_id() {
        return Admin_id;
    }

    public void setAdmin_id(String admin_id) {
        Admin_id = admin_id;
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

    public Integer getConstraint() {
        return constraint;
    }

    public void setConstraint(Integer constraint) {
        this.constraint = constraint;
    }
}
