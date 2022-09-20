package com.creativeminds.app.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="image")
    private String image;

    @Column(name="aoth0Id", unique = true)
    private String aoth0Id;

    public User(){
    }

    public User(String email, String image, String aoth0Id) {
        this.email = email;
        this.image = image;
        this.aoth0Id = aoth0Id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAoth0Id() {
        return aoth0Id;
    }

    public void setAoth0Id(String aoth0Id) {
        this.aoth0Id = aoth0Id;
    }
}
