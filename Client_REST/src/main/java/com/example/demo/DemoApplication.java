package com.example.demo;


import com.example.demo.web.model.Facture;
import com.example.demo.web.model.Produit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class DemoApplication {


	public static final String REST_SERVICE_URI="http://192.168.56.101:8090";


	/* POST */
	private static void createFactureWithProducts() {
		RestTemplate restTemplate = new RestTemplate();
		Facture facture = new Facture();
		facture.setId(0);
		facture.getProduits().add(new Produit("patate",5));
		facture.getProduits().add(new Produit("kiwi",9));
		facture.getProduits().add(new Produit("patate",52));
		facture.getProduits().add(new Produit("fekir",53));
		facture.getProduits().add(new Produit("orange",45));
		facture.getProduits().add(new Produit("banane",55));
		facture.getProduits().add(new Produit("fekir",56));
		facture.getProduits().add(new Produit("banane",57));
		facture.getProduits().add(new Produit("banane",85));
		facture.getProduits().add(new Produit("banane",95));
		facture.getProduits().add(new Produit("pomme",55));
		facture.getProduits().add(new Produit("pomme",45));


		//sauvegarder la facture
		Facture result = restTemplate.postForObject( REST_SERVICE_URI+"/api/genererFacture", facture, Facture.class);

		//sauvegarder les produits
		for (Produit produit:facture.getProduits()) {
			produit.setFk_FactureID(result.getId());
			Produit result1 = restTemplate.postForObject( REST_SERVICE_URI+"/api/genererProduits", produit, Produit.class);
		}

	}

	/* GET */
	private static void getFrequentProduct() {
		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate.getForObject(REST_SERVICE_URI+"/api/produitFrequent",  String.class);

		//afficher les produits les plus fréquents
		System.out.println(response.toString());

	}


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		createFactureWithProducts();
		getFrequentProduct();
	}


}
