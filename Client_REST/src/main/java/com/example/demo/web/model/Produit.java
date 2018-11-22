package com.example.demo.web.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Produit {

    @PrimaryKey
    private int id;

    private int fk_FactureID;

    private String nameProduit;

    private int prix;

    public Produit(){}

    public Produit( String nameProduit, int prix){
        this.setId(1);
        this.setFk_FactureID(1);
        this.setNameProduit(nameProduit);
        this.setPrix(prix);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_FactureID() {
        return fk_FactureID;
    }

    public void setFk_FactureID(int fk_FactureID) {
        this.fk_FactureID = fk_FactureID;
    }

    public String getNameProduit() {
        return nameProduit;
    }

    public void setNameProduit(String nameProduit) {
        this.nameProduit = nameProduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}