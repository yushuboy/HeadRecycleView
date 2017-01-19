package com.axaet.myrecycleview.beans;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/6.
 */

public class DataModel {

    public final static int TYPE_ONE = 0x01;
    public final static int TYPE_TWO = 0x02;
    public final static int TYPE_THREE = 0x03;

    public int type;
    public String title;
    public String url1;
    public String url2;
    public String url3;
    public String time;


    public ArrayList<String> list=new ArrayList<>();

}
