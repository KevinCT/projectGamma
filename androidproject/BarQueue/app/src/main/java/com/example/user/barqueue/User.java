package com.example.user.barqueue;

import com.firebase.client.Firebase;

/**
 * Created by User on 4/27/2016.
 */
public class User {
    private int birthYear;
    private String fullName;
    public User() {}
    public User(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }
    public long getBirthYear() {
        return birthYear;
    }
    public String getFullName() {
        return fullName;
    }
    Firebase ref = new Firebase("https://blinding-heat-4643.firebaseio.com/ericshao");
    Firebase alanRef = ref.child("users").child("alanisawesome");
    User alan = new User("Alan Turing", 1912);

}



