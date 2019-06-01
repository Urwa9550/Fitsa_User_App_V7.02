package com.wasisoft.fitsa_user_app.Model;

import android.net.Uri;

public class Nutritionist_items extends GeneralUser {

    private String mItemName, mQualification, mInstitute;
    private String mExperience;
    private String uid;
    private String mImageUriStrl;

    public Nutritionist_items() {
        super();
    }

    public Nutritionist_items(String mItemName, String mQualification, String mInstitute, String mExperience, String mImageUriStrl, String uid) {

        super(mItemName, mQualification, mInstitute, mExperience, mImageUriStrl, uid);

        this.mItemName = mItemName;
        this.mQualification = mQualification;
        this.mInstitute = mInstitute;
        this.mExperience = mExperience;
        this.mImageUriStrl = mImageUriStrl;
        this.uid = uid;

    }


    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public String getmQualification() {
        return mQualification;
    }

    public void setmQualification(String mQualification) {
        this.mQualification = mQualification;
    }

    public String getmInstitute() {
        return mInstitute;
    }

    public void setmInstitute(String mInstitute) {
        this.mInstitute = mInstitute;
    }

    public String getmExperience() {
        return mExperience;
    }

    public void setmExperience(String mExperience) {
        this.mExperience = mExperience;
    }

    public String getmImageUriStrl() {
        return mImageUriStrl;
    }

    public void setmImageUriStrl(String mImageUriStrl) {
        this.mImageUriStrl = mImageUriStrl;
    }

    public String getUid() {
        return uid;
    }

    public void setgetUid(String uid) {
        this.uid = uid;
    }

}
