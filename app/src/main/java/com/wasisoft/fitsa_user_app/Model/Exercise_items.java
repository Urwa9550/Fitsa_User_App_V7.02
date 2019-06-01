package com.wasisoft.fitsa_user_app.Model;

public class Exercise_items {
        private String mItemName;
        private String mItemDescription;
        private int mItemImages;

        public Exercise_items(){}

        public Exercise_items(String mItemName,String mItemDescription,int mItemImages ) {
            this.mItemName = mItemName;
            this.mItemImages = mItemImages;
            this.mItemDescription= mItemDescription;
        }

        public String getmItemName() {
            return mItemName;
        }

        public void setmItemName(String mItemName) {
            this.mItemName = mItemName;
        }

        public String getmItemDescription() {
        return mItemDescription;
    }

        public void setmItemDescription(String mItemDescription) {
        this.mItemDescription = mItemDescription;
        }

        public int getmItemImages() {
            return mItemImages;
        }

        public void setmItemImages(int mItemImages) {
            this.mItemImages = mItemImages;
        }
        }



