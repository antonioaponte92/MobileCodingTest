package com.antonio.mobilecodingtest.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("transport")
    @Expose
    private String transport;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("geocoordinates")
    @Expose
    private String geocoordinates;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("phone")
    @Expose
    private String phone;

    public PointDetails(String id, String title, String address, String transport, String email, String geocoordinates, String description, String phone) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.transport = transport;
        this.email = email;
        this.geocoordinates = geocoordinates;
        this.description = description;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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