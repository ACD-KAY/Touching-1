package com.hhu.acd.touching;

/**
 * Created by liziming on 18-2-17.
 */


public class okhttpurl {
    public  static String url_sendmsg="http://106.14.195.234/springmvc/user/sendMessage";//发送验证短信
    public  static String url_registeruser="http://106.14.195.234/springmvc/user/registeruser";//注册用户
    public  static String url_sendmsg_test="http://192.168.1.107/springmvc/user/sendMessage";
    public  static String url_registeruser_test="http://192.168.1.107/springmvc/user/registeruser";
    public  static String url_image="http://106.14.195.234/springmvc/images/000.jpeg";
    public  static String[] members= {""};
    public  static String url_createmeeting="http://106.14.195.234/springmvc/meeting/createmeeting";//创建会议，post传参Meetings类的json数据
    public  static String url_searchmeetingbytime="http://106.14.195.234/springmvc/meeting/searchmeetingbytime";//查找会议，通过时间，最多返回10条记录，无需post传参
    public  static String url_searchmeetingbyname="http://106.14.195.234/springmvc/meeting/searchmeetingbyname";//查找会议，通过关键字，post传参paramter “name”
    
}
