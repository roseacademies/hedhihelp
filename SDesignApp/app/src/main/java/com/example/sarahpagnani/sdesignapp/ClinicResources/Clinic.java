package com.example.sarahpagnani.sdesignapp.ClinicResources;

import java.io.Serializable;

/**
 * Created by SarekSoteloJimenez on 5/5/18.
 */

public class Clinic implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String latlong;

    public Clinic(String _name, String _address, String _phone, String _latlong){
        this.name = _name;
        this.address = _address;
        this.phone = _phone;
        this.latlong = _latlong;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }
}