package modele;

public class Demande {

    private int idDemande;
    private String nomFourniture;
    private int quantiter;
    private int refProf;
    private int refGestionaire;

    private String raison;

    public Demande(int idDemande,String nomFourniture,int quantiter,int refProf,int refGestionaire, String raison){
        this.idDemande=idDemande;
        this.nomFourniture=nomFourniture;
        this.quantiter=quantiter;
        this.refProf=refProf;
        this.refGestionaire=refGestionaire;
        this.raison=raison;
    }

    public Demande(String nomFourniture,int quantiter,int refProf,int refGestionaire, String raison){
        this.nomFourniture=nomFourniture;
        this.quantiter=quantiter;
        this.refProf=refProf;
        this.refGestionaire=refGestionaire;
        this.raison=raison;
    }

    public Demande(String nomFourniture,int quantiter,int refProf, String raison){
        this.nomFourniture=nomFourniture;
        this.quantiter=quantiter;
        this.refProf=refProf;
        this.raison=raison;
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

    public int getrefGestionaire() {
        return refGestionaire;
    }

    public void setrefGestionaire(int refGestionaire) {
        this.refGestionaire = refGestionaire;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}
