package com.antonio.mobilecodingtest.data.local;

import com.orm.SugarRecord;

public class PointDetailsTable extends SugarRecord {
    String identifier;
    String title;
    String address;
    String transport;
    String email;
    String geocoordinates;
    String description;
    String phone;

    public PointDetailsTable() {
    }

    public PointDetailsTable(String identifier, String title, String address, String transport, String email, String geocoordinates, String description, String phone) {
        this.identifier = identifier;
        this.title = title;
        this.address = address;
        this.transport = transport;
        this.email = email;
        this.geocoordinates = geocoordinates;
        this.description = description;
        this.phone = phone;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdientifier(String id) {
        this.identifier = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeocoordinates() {
        return geocoordinates;
    }

    public void setGeocoordinates(String geocoordinates) {
        this.geocoordinates = geocoordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
