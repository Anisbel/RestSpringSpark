package com.example.demo.Dao;

import java.util.List;

import com.example.demo.web.model.Produit;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Produit, String> {

    @Query(value="SELECT * FROM produit WHERE nameProduit=?0 ALLOW FILTERING")
    public List<Produit> findByProductname(String nameProduit);

    @Query("SELECT * FROM produit WHERE prix > ?0 ALLOW FILTERING")
    public List<Produit> findProductHasPriceGreaterThan(int prix);
}