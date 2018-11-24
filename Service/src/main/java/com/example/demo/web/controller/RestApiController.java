package com.example.demo.web.controller;


import com.example.demo.Dao.FactureRepository;
import com.example.demo.Dao.ProductRepository;
import com.example.demo.web.model.Facture;
import com.example.demo.web.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
public class RestApiController {



    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FactureRepository factureRepository;

    public static String REST_SERVICE_SPARK = "http://localhost:8080";

    //fonction appelé par le client. Sauvegarde des produits dans la BD en lui fournissant des ID unique
    @PostMapping("/api/genererProduits")
    public ResponseEntity<String> createFactureWithProduct( @RequestBody Produit produit) {


            produit.setId(randomId());
            productRepository.save(produit);

        return new ResponseEntity(HttpStatus.CREATED);


    }

    //fonction appelé par le client. Sauvegarde une facture dans la BD en lui fournissant un ID unique
    @PostMapping("/api/genererFacture")
    public Facture createFacture(@RequestBody Facture facture) {

        facture.setId(randomId());
        factureRepository.save(facture);

        return facture;

    }

    // Fonction appelé par le client. Elle fait un appel Rest au serviceSpark pour connaitres les éléments les plus fréquents.
    // Elle retourne la réponse au client.
    @GetMapping("/api/produitFrequent")
    public ResponseEntity<String> getFrequentProduct() {

        RestTemplate restTemplate = new RestTemplate();


        String response = restTemplate.getForObject(REST_SERVICE_SPARK+"/api/produitFrequent",  String.class);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);

    }


    public int randomId(){

    Random rand = new Random();

    int id = rand.nextInt(10000) + 1;
    return id;

    }

}


