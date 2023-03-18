package modele;

public class FicheEtudiant {
private int idFiche;

private String nom;

private String prenom;

private String email;

private String diplome;

private int telephone;

private String rue;

private int cp;
private String ville;
private String adresse;
private int ref_utilisateur;

private int ref_dossier;

    public FicheEtudiant(int idFiche, String nom, String prenom, String email, String diplome, int telephone, String rue, int cp, String ville, int ref_utilisateur){
        this.idFiche = idFiche;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.diplome = diplome;
        this.telephone = telephone;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.adresse = rue+" "+ville+" "+cp;
        this.ref_utilisateur = ref_utilisateur;
    }
    public FicheEtudiant( String nom, String prenom, String email, String diplome, int telephone, String rue, int cp, String ville, int ref_utilisateur){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.diplome = diplome;
        this.telephone = telephone;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.ref_utilisateur = ref_utilisateur;
    }

    public int getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(int idFiche) {
        this.idFiche = idFiche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
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

    public int getRef_utilisateur() {
        return ref_utilisateur;
    }
    public String getAdresse() {
        return adresse;
    }

    public int getRef_dossier() {
        return ref_dossier;
    }

    public void setRef_dossier(int ref_dossier) {
        this.ref_dossier = ref_dossier;
    }

    public void setRef_utilisateur(int ref_utilisateur) {
        this.ref_utilisateur = ref_utilisateur;
    }
}
