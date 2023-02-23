package modele;

public class Utilisateur {
    private int idUtilisateur;
    private String prenom;
    private String nom;
    private String email;
    private Integer role;
    private String mdp;
    private String code;

    public Utilisateur(int idUtilisateur, String nom, String prenom, String email, String mdp, int role) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.role = role;
        this.mdp = mdp;
    }

    public Utilisateur(int idUtilisateur, String nom, String prenom, String email, int role) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String email,String mdp ,int role) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.role = role;
        this.mdp = mdp;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
