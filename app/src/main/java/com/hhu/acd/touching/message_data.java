package com.hhu.acd.touching;

/**
 * Created by liziming on 18-1-24.
 */
public class message_data {
    private int imgurl;
    private String name;
    private String massage;
    private int user_id;
    public message_data(int imgurl, String name, String massage, int user_id){
        this.imgurl=imgurl;
        this.name=name;
        this.massage=massage;
        this.user_id=user_id;
    }
    protected int getImgurl(){
        return this.imgurl;
    }
    protected String getName(){
        return this.name;
    }
    protected String getMassage(){
        return this.massage;
    }
    protected int getUser_id(){return this.user_id;}
}

