package com.hhu.acd.touching;

public class Userinfos {
    private String userid;

    private String nick;

    private String password;

    public String getUserId() {
        return userid;
    }

    public void setUserId(String id) {
        this.userid = id == null ? null : id.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String usersName) {
        this.nick = usersName == null ? null : usersName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String usersPassword) {
        this.password = usersPassword == null ? null : usersPassword.trim();
    }
}