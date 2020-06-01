package com.shubhcode.nmcourse;

public class ClassUserPickedChapter {
    public String titleUser;
    public String picUser;

    public ClassUserPickedChapter() {
    }

    public ClassUserPickedChapter(String titleUser, String picUser) {
        this.titleUser = titleUser;
        this.picUser = picUser;
    }

    public String getTitleUser() {
        return titleUser;
    }

    public void setTitleUser(String titleUser) {
        this.titleUser = titleUser;
    }

    public String getPicUser() {
        return picUser;
    }

    public void setPicUser(String picUser) {
        this.picUser = picUser;
    }
}
