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

    @PostMapping("/api/genererProduits")
    public ResponseEntity<String> createFactureWithProduct( @RequestBody Produit produit) {


            produit.setId(randomId());
            productRepository.save(produit);

        return new ResponseEntity(HttpStatus.CREATED);


    }


    @PostMapping("/api/genererFacture")
    public Facture createFacture(@RequestBody Facture facture) {

        facture.setId(randomId());
        factureRepository.save(facture);

        return facture;

    }


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


