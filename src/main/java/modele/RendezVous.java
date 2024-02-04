package modele;

public class RendezVous {

    private int id_RendezVous;

    private String date;
    private String heure;
    private int salle;
    private int ref_prof;
    private int ref_dossier;


    public RendezVous(int id_rdv, String date, String heure, int salle, int ref_prof, int ref_dossier) {
        this.id_RendezVous = id_rdv;
        this.date = date;
        this.heure = heure;
        this.salle = salle;
        this.ref_prof = ref_prof;
        this.ref_dossier = ref_dossier;
    }
    public RendezVous(String date, String heure, int salle, int ref_prof, int ref_dossier) {
        this.date = date;
        this.heure = heure;
        this.salle = salle;
        this.ref_prof = ref_prof;
        this.ref_dossier = ref_dossier;
    }

    public int getId_RendezVous() {
        return id_RendezVous;
    }

    public void setId_RendezVous(int id_RendezVous) {
        this.id_RendezVous = id_RendezVous;
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

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    public int getRef_prof() {
        return ref_prof;
    }

    public int getRef_dossier() {
        return ref_dossier;
    }

    public void setRef_dossier(int ref_dossier) {
        this.ref_dossier = ref_dossier;
    }
}


