package com.hhu.acd.touching;

/**
 * Created by wei on 2018/3/17.
 */

public class itembean {
    public boolean visible;
    public String selection;
    public itembean(boolean visible,String selection){
        this.visible=visible;
        this.selection=selection;
    }
    public boolean getVisible(){
        return this.visible;
    }
    public String getSelection(){
        return this.selection;
    }
    public void setSelection(String s){
        this.selection=s;
    }

}