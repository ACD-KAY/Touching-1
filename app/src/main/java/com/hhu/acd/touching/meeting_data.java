package com.hhu.acd.touching;

/**
 * Created by liziming on 18-1-31.
 */

public class meeting_data {
    private int imgurl;
    private String meeting_name;
    private String meeting_time;
    private String meeting_place;
    private int user_id;
    public meeting_data(int imgurl,String meeting_name,String meeting_time,String meeting_place,int user_id){
        this.imgurl=imgurl;
        this.meeting_name=meeting_name;
        this.meeting_time=meeting_time;
        this.meeting_place=meeting_place;
        this.user_id=user_id;

    }
    protected int getImgurl(){
        return this.imgurl;
    }
    protected String getMeeting_name(){
        return this.meeting_name;
    }
    protected String getMeeting_time(){
        return this.meeting_time;
    }
    protected String getMeeting_place(){
        return this.meeting_place;
    }
    protected int getUser_id(){
        return this.user_id;
    }
}
