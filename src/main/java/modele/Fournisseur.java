package modele;

public class Fournisseur {
    private int id_fournisseur;
    private String nom_entreprise;
    private  int ref_utilisateur;
    private String rue;
    private int cp;
    private String ville;

    public  Fournisseur (int id_fournisseur, String nom_entreprise, String rue, int cp, String ville){
        this.id_fournisseur=id_fournisseur;
        this.nom_entreprise=nom_entreprise;
        this.rue = rue;
        this.cp=cp;
        this.ville=ville;
    }
    public  Fournisseur (String nom_entreprise, String rue, int cp, String ville,int ref_utilisateur){
        this.nom_entreprise=nom_entreprise;
        this.rue = rue;
        this.cp=cp;
        this.ville=ville;
        this.ref_utilisateur = ref_utilisateur;
    }


    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getRue() {
        return rue;
    }

    public int getRef_utilisateur() {
        return ref_utilisateur;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
