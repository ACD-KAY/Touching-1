package com.hhu.acd.touching;

/**
 * Created by liziming on 18-1-17.
 */

public class Model {

    private String title;
    private String content;
    private String imgUrl;

    //生成set、get方法
    void setTitle(String title)
    {
        this.title=title;

    }
    void setContent(String content)
    {
        this.content=content;

    }
    void setImgUrl(String imgUrl)
    {
        this.imgUrl=imgUrl;

    }
    String getTitle()
    {
        return  this.title;
    }
    String getContent()
    {
        return  this.content;
    }
}
