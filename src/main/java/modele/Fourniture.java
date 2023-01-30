package modele;

public class Fourniture {
private int idFourniture;
private String nom;
private int stock;
private String fournisseur;
    private int code;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public Fourniture(int idFourniture, String nom, int stock, String fournisseur){
        this.idFourniture = idFourniture;
        this.nom = nom;
        this.stock = stock;
        this.fournisseur = fournisseur;
    }

    public int getIdFourniture() {
        return idFourniture;
    }

    public void setIdFourniture(int idFourniture) {
        this.idFourniture = idFourniture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
