package modele;

public class Demande {

    private int idDemande;
    private String nomFourniture;
    private int quantiter;
    private int refProf;
    private int refGestionair;

    public Demande(int idDemande,String nomFourniture,int quantiter,int refProf,int refGestionair){
        this.idDemande=idDemande;
        this.nomFourniture=nomFourniture;
        this.quantiter=quantiter;
        this.refProf=refProf;
        this.refGestionair=refGestionair;
    }

    public Demande(String nomFourniture,int quantiter,int refProf,int refGestionair){
        this.nomFourniture=nomFourniture;
        this.quantiter=quantiter;
        this.refProf=refProf;
        this.refGestionair=refGestionair;
    }

    public Demande(String nomFourniture,int quantiter,int refProf){
        this.nomFourniture=nomFourniture;
        this.quantiter=quantiter;
        this.refProf=refProf;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public String getNomFourniture() {
        return nomFourniture;
    }

    public void setNomFourniture(String nomFourniture) {
        this.nomFourniture = nomFourniture;
    }

    public int getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(int quantiter) {
        this.quantiter = quantiter;
    }

    public int getRefProf() {
        return refProf;
    }

    public void setRefProf(int refProf) {
        this.refProf = refProf;
    }

    public int getRefGestionair() {
        return refGestionair;
    }

    public void setRefGestionair(int refGestionair) {
        this.refGestionair = refGestionair;
    }
}
