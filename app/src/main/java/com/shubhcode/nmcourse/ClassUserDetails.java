package com.shubhcode.nmcourse;

public class ClassUserDetails {
    private String mName;
    private String mEail;
    private String mPhoneNumber;
    private String mImageuri;

    public ClassUserDetails() {
    }

    public ClassUserDetails(String mName, String mEail, String mPhoneNumber, String mImageuri) {
        this.mName = mName;
        this.mEail = mEail;
        this.mPhoneNumber = mPhoneNumber;
        this.mImageuri = mImageuri;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEail() {
        return mEail;
    }

    public void setmEail(String mEail) {
        this.mEail = mEail;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmImageuri() {
        return mImageuri;
    }

    public void setmImageuri(String mImageuri) {
        this.mImageuri = mImageuri;
    }
}
