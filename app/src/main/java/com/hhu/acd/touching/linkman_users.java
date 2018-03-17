package com.hhu.acd.touching;

/**
 * Created by liziming on 18-1-29.
 */

public class linkman_users {
    private int imgId;
    private String nickName;
    private boolean isOnline;
    //private String sign;


    public linkman_users() {
        super();
    }
    public linkman_users(int imgId, String nickName, boolean isOnline) {
        super();
        this.imgId = imgId;
        this.nickName = nickName;
        this.isOnline = isOnline;
        //this.sign = sign;
    }

    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public boolean isOnline() {
        return isOnline;
    }
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
    /*public String getSign() {
        return sign;
    }*/
    /*public void setSign(String sign) {
        this.sign = sign;
    }
*/



}
