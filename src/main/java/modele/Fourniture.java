package modele;

public class Fourniture {
    private int idFourniture;
    private String nom;
    private int stock;
    private String fournisseur;

    private int ref_utilisateur;

    public Fourniture(int idFourniture, String nom, int stock) {
        this.idFourniture = idFourniture;
        this.nom = nom;
        this.stock = stock;
    }

    public Fourniture(String nom, int stock, int ref_utilisateur) {
        this.nom = nom;
        this.stock = stock;
        this.ref_utilisateur = ref_utilisateur;
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

    public int getRef_utilisateur() {
        return ref_utilisateur;
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
