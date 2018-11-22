package com.example.demo.web.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table
public class Facture {

    @PrimaryKey
    private int id;

 //   private ArrayList<Produit> produits;

    public Facture(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}