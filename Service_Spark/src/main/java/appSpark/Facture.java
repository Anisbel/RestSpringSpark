package appSpark;


public class Facture {
    private String produits;
    private long prix;

    public Facture() {
    }

    public Facture(String produits, long prix) {
        this.setProduits(produits);
        this.setPrix(prix);
    }


    public String getProduits() {
        return produits;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }

    public long getPrix() {
        return prix;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }
}
