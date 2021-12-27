package com.example.hp.pfa_app;

import java.util.ArrayList;

/**
 * Created by hp on 16/04/2021.
 */

public class Orphelin extends ArrayList<String> {

    int id;
    private String nom;
    private String prenom;
    private int age;
    private String nom_mere;
    private String cin_mere;
    private int tel;
    private String niveau_scolaire;
    private String adresse;
    private String image;


    public Orphelin(int id, String nom, String prenom, int age, String nom_mere, String cin_mere, int tel, String niveau_scolaire, String adresse, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nom_mere = nom_mere;
        this.cin_mere = cin_mere;
        this.tel = tel;
        this.niveau_scolaire = niveau_scolaire;
        this.adresse = adresse;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom_mere() {
        return nom_mere;
    }

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getCin_mere() {
        return cin_mere;
    }

    public void setCin_mere(String cin_mere) {
        this.cin_mere = cin_mere;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNiveau_scolaire() {
        return niveau_scolaire;
    }

    public void setNiveau_scolaire(String niveau_scolaire) {
        this.niveau_scolaire = niveau_scolaire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
