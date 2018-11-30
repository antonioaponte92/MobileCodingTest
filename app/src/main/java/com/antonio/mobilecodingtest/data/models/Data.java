package com.antonio.mobilecodingtest.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("list")
    @Expose
    private List<Point> list;

    public Data(List<Point> list) {
        this.list = list;
    }

    public List<Point> getList() {
        return list;
    }

    public void setList(List<Point> list) {
        this.list = list;
    }
}
