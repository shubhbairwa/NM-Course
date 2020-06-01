package com.shubhcode.nmcourse;

public class ClassChapterDetails {
    public String mTitleOfChapter;
    public String mFullDescriptionOfChapter;
    public String mThumbnailofChapter;
    public String mJoin;


    public ClassChapterDetails(String mTitleOfChapter, String mFullDescriptionOfChapter, String mThumbnailofChapter, String mJoin) {
        this.mTitleOfChapter = mTitleOfChapter;
        this.mFullDescriptionOfChapter = mFullDescriptionOfChapter;
        this.mThumbnailofChapter = mThumbnailofChapter;
        this.mJoin = mJoin;
    }

    public ClassChapterDetails() {


    }

    public void changeText(String text1) {
        mJoin = text1;
    }

    public String getmTitleOfChapter() {
        return mTitleOfChapter;
    }

    public void setmTitleOfChapter(String mTitleOfChapter) {
        this.mTitleOfChapter = mTitleOfChapter;
    }

    public String getmFullDescriptionOfChapter() {
        return mFullDescriptionOfChapter;
    }

    public void setmFullDescriptionOfChapter(String mFullDescriptionOfChapter) {
        this.mFullDescriptionOfChapter = mFullDescriptionOfChapter;
    }

    public String getmThumbnailofChapter() {
        return mThumbnailofChapter;
    }

    public void setmThumbnailofChapter(String mThumbnailofChapter) {
        this.mThumbnailofChapter = mThumbnailofChapter;
    }

    public String getmJoin() {
        return mJoin;
    }

    public void setmJoin(String mJoin) {
        this.mJoin = mJoin;
    }
}
