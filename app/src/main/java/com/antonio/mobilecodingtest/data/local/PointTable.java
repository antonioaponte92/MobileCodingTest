package com.antonio.mobilecodingtest.data.local;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PointTable extends SugarRecord{
    @Unique
    String id;
    String title;
    String geocoordinates;

    public PointTable() {}

    public PointTable(String id, String title, String geocoordinates) {
        this.id = id;
        this.title = title;
        this.geocoordinates = geocoordinates;
    }


    public String getIdentifier() {
        return id;
    }

    public void setIdentifier(String id) {
        this.id = id;
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
