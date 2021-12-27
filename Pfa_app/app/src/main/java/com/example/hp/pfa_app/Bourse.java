package com.example.hp.pfa_app;

/**
 * Created by hp on 17/06/2021.
 */

public class Bourse extends Orphelin {
    double montant;

    public Bourse(int id, String nom, String prenom, int age, String nom_mere, String cin_mere, int tel, String niveau_scolaire, String adresse, String image, double montant) {
        super(id, nom, prenom, age, nom_mere, cin_mere, tel, niveau_scolaire, adresse, image);
        this.montant = montant;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
