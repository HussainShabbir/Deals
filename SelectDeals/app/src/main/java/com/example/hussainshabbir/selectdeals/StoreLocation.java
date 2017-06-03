package com.example.hussainshabbir.selectdeals;

import org.json.JSONArray;

/**
 * Created by HussainShabbir on 6/3/17.
 */

public class StoreLocation {

    String number;
    String name;
    String country;
    JSONArray coordinates;
    String streetAddress;
    String city;
    String stateProvCode;
    String zip;
    String phoneNumber;
    boolean sundayOpen;
    String timeZone;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvCode() {
        return stateProvCode;
    }

    public void setStateProvCode(String stateProvCode) {
        this.stateProvCode = stateProvCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(boolean sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public JSONArray getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(JSONArray coordinates) {
        this.coordinates = coordinates;
    }
}
