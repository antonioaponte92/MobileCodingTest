package com.antonio.mobilecodingtest.data.local;

import com.orm.SugarRecord;

public class PointTable extends SugarRecord{
    String identifier;
    String title;
    String geocoordinates;

    public PointTable() {

    }

    public PointTable(String identifier, String title, String geocoordinates) {
        this.identifier = identifier;
        this.title = title;
        this.geocoordinates = geocoordinates;
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String id) {
        this.identifier = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGeocoordinates() {
        return geocoordinates;
    }

    public void setGeocoordinates(String geocoordinates) {
        this.geocoordinates = geocoordinates;
    }
}
