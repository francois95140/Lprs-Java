package modele;

public class DossierInscripition {

    private int id_dossier;
    private String date;
    private String eleve;
    private String heure;
    private String filiere;
    private String motivation;
    private int ref_fiche;
    private boolean entretien;


    public DossierInscripition(int id_dossier, String date, String heure, String filiere, String motivation, int ref_fiche, boolean entretien, String eleven, String elevep) {
        this.id_dossier = id_dossier;
        this.date = date;
        this.heure = heure;
        this.filiere = filiere;
        this.motivation = motivation;
        this.ref_fiche = ref_fiche;
        this.entretien = entretien;
        this.eleve = eleven + " " + elevep;
    }
    public DossierInscripition(String filiere, String motivation, int ref_fiche) {
        this.filiere = filiere;
        this.motivation = motivation;
        this.ref_fiche = ref_fiche;
    }

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public int getRef_fiche() {
        return ref_fiche;
    }

    public void setRef_fiche(int ref_fiche) {
        this.ref_fiche = ref_fiche;
    }

    public String getEleve() {
        return eleve;
    }

    public boolean isEntretien() {
        return entretien;
    }

    public void setEntretien(boolean entretien) {
        this.entretien = entretien;
    }
}









