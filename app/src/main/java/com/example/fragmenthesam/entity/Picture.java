package com.example.fragmenthesam.entity;

public class Picture {
    private int PicID;

    public Picture(int headPicID) {
        PicID = headPicID;
    }

    public int getPicID() {
        return PicID;
    }

    public void setPicID(int picID) {
        PicID = picID;
    }
}
