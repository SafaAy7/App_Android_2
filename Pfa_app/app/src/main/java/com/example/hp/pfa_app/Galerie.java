package com.example.hp.pfa_app;

/**
 * Created by hp on 17/06/2021.
 */

public class Galerie {
   private  int id;
   private  String titre;
   private  String contenu;
   private  int minage;
   private  int maxage;
    private String image;

    public Galerie(int id, String titre, String contenu, int minage, int maxage, String image) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.minage = minage;
        this.maxage = maxage;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getMinage() {
        return minage;
    }

    public void setMinage(int minage) {
        this.minage = minage;
    }

    public int getMaxage() {
        return maxage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }
}
