package com.example.hp.pfa_app;

import java.util.Date;

/**
 * Created by hp on 01/05/2021.
 */

public class Evenment {
    private int id;
    private String nom;
    private String date;
    private String lieu;
    private String contact;
    private String description;
    private String responsable;
    private String image;

    public Evenment(int id, String nom, String date, String lieu, String contact, String description, String responsable, String image) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.contact = contact;
        this.description = description;
        this.responsable = responsable;
        this.image = image;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
