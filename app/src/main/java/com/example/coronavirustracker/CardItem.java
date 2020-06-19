package com.example.coronavirustracker;

public class CardItem {

    private String mState;
    private String mActive;
    private String mConfirmed;
    private String mDeceased;
    private String mRecovered ;

    public CardItem(String mState, String mActive, String mConfirmed, String mDeceased, String mRecovered) {
        this.mState = mState;
        this.mActive = mActive;
        this.mConfirmed = mConfirmed;
        this.mDeceased = mDeceased;
        this.mRecovered = mRecovered;
    }

    public String getmState() {
        return mState;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmConfirmed() {
        return mConfirmed;
    }

    public String getmDeceased() {
        return mDeceased;
    }

    public String getmRecovered() {
        return mRecovered;
    }


}
