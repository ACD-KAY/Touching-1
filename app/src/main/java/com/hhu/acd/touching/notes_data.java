package com.hhu.acd.touching;

/**
 * Created by liziming on 18-2-2.
 */

public class notes_data {
    private String notestime;
    private String notescontent;
    protected notes_data(String notestime, String notescontent){
        this.notestime=notestime;
        this.notescontent=notescontent;

    }
    protected String getNotestime(){
        return this.notestime;
    }
    protected String getNotescontent(){
        return this.notescontent;
    }
}
