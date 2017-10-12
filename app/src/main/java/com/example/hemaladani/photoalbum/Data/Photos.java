package com.example.hemaladani.photoalbum.Data;

import java.io.Serializable;

/**
 * Created by hemaladani on 10/9/17.
 */

public class Photos implements Serializable {

    private String mImageName, mTitle,mUrl;

    public String getmImageName() {
        return mImageName;
    }

    public void setmImageName(String mImageName) {
        this.mImageName = mImageName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
