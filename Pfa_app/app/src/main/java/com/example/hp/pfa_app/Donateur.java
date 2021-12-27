package com.example.hp.pfa_app;

/**
 * Created by hp on 17/06/2021.
 */

public class Donateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String montantsoutien;
    private String image;


    public Donateur(int id, String nom, String prenom, String email, String phone, String montantsoutien, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.montantsoutien = montantsoutien;
        this.image = image;
    }

    public String getMontantsoutien() {
        return montantsoutien;
    }

    public void setMontantsoutien(String montantsoutien) {
        this.montantsoutien = montantsoutien;
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
