package com.example.sarahpagnani.sdesignapp.DataModels;

/**
 * Created by SarekSoteloJimenez on 1/19/18.
 */

public class AppUser {
    public String UID;
    public String name;
    public String email;
    public String age;

    public AppUser() {

    }

    public AppUser(String UID, String name, String email, String age) {
        this.UID = UID;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
