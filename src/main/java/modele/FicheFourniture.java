package modele;

public class FicheFourniture {

    private int id_fiche_fourniture;
    private int ref_fournisseur;
    private double prix;
    private int ref_fourniture;
    private  int ref_utilisateur;

    public FicheFourniture(int id_fiche_fourniture, int ref_fournisseur, double prix, int ref_fourniture,int ref_utilisateur) {
        this.id_fiche_fourniture = id_fiche_fourniture;
        this.ref_fourniture = ref_fourniture;
        this.prix = prix;
        this.ref_fournisseur = ref_fournisseur;
        this.ref_utilisateur = ref_utilisateur;
    }
    public FicheFourniture(int ref_fournisseur, int ref_fourniture,double prix, int ref_utilisateur ) {
        this.ref_fourniture = ref_fourniture;
        this.prix = prix;
        this.ref_fournisseur = ref_fournisseur;
        this.ref_utilisateur = ref_utilisateur;
    }

    public int getId_fiche_fourniture() {
        return id_fiche_fourniture;
    }

    public double getPrix() {
        return prix;
    }

    public int getRef_utilisateur() {
        return ref_utilisateur;
    }

    public void setId_fiche_fourniture(int id_fiche_fourniture) {
        this.id_fiche_fourniture = id_fiche_fourniture;
    }

    public void setprix(double prix) {
        this.prix = prix;
    }

    public int getRef_fournisseur() {
        return ref_fournisseur;
    }

    public void setRef_fournisseur(int ref_fournisseur) {
        this.ref_fournisseur = ref_fournisseur;
    }

    public int getRef_fourniture() {
        return ref_fourniture;
    }

    public void setRef_fourniture(int ref_fourniture) {
        this.ref_fourniture = ref_fourniture;
    }
}






