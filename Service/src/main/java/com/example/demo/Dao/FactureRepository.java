package com.example.demo.Dao;

import java.util.List;

import com.example.demo.web.model.Facture;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FactureRepository extends CrudRepository<Facture, String> {

    @Query(value="SELECT * FROM facture WHERE id=?0 ALLOW FILTERING")
    public List<Facture> findFactureByID(int id);

}