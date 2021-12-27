package com.example.hp.pfa_app;

/**
 * Created by hp on 15/05/2021.
 */

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private int cin;
    private String responsablite;
    private String image;

    public Membre(int id, String nom, String prenom, int cin, String responsablite, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.responsablite = responsablite;
        this.image = image;
    }

    public int getId() {
        return id;
    }


    public String getResponsablite() {
        return responsablite;
    }

    public void setResponsablite(String responsablite) {
        this.responsablite = responsablite;
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

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
