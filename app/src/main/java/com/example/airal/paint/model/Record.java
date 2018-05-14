package com.example.airal.paint.model;

import com.example.airal.paint.tool.TouchImageView;

/**
 * Created by airal on 2018/4/28.
 */

public class Record {
    public static int TAG_ADD=0,TAG_TOUCH=1;
    public int tag=TAG_ADD;
    public TouchImageView view;
    public float scale=1f;
    public float x=0f;
    public float y=0f;
    public float rotato=0f;
    public float rotatoY=0f;
}
