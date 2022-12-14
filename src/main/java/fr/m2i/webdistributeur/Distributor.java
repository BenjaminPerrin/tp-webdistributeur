package fr.m2i.webdistributeur;

import java.util.ArrayList;
import java.util.List;

public class Distributor {

    private static Distributor instance;
    
    private int credit;
    private List<Product> stock;
    
    public static Distributor getInstance() {
        
        if (instance == null) {
            instance = new Distributor();
        }

        return instance;
    }
    
    private Distributor() {
        credit = 0;
        stock = new ArrayList();
        remplirLeStock();
    }
    
    public void remplirLeStock() {
        stock.add(new Product(1, "café", 1, 5));
        stock.add(new Product(2, "soda", 2, 5));
        stock.add(new Product(3, "barre céréales", 3, 5));
    }

    public void insererArgent(int montant) {
        credit += montant;
    }

    public boolean stockSuffisant(int idProduit) {
        Product produit = getProduit(idProduit);
        return produit != null && produit.getQuantite() >= 1;
    }

    public boolean creditSuffisant(int idProduit) {
        Product produit = getProduit(idProduit);
        return produit != null && credit >= produit.getPrix();
    }

    public void commanderProduit(int idProduit){

        if(stockSuffisant(idProduit) && creditSuffisant(idProduit)) {
            Product produit = getProduit(idProduit);

            produit.setQuantite(produit.getQuantite() - 1);
            credit -= produit.getPrix(); 
        }
    }
    
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<Product> getStock() {
        return stock;
    }

    public void setStock(List<Product> stock) {
        this.stock = stock;
    }
    
    public Product getProduit(int id) {
        for (Product produit: stock) {
           if (produit.getId() == id){
               return produit;
           }
        }
        return null;
    }
    
}