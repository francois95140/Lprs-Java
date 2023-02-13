package modele;

public class RendezVous {

        private int id_rdv;

        private String date;
        private String heure;
        private String salle;
        private String RefUtilisateur;

        public RendezVous( int id_rdv, String date, String heure, String salle, String RefUtilisateur) {
            this.id_rdv = id_rdv;
            this.date = date;
            this.heure = heure;
            this.salle = salle;
            this.RefUtilisateur = RefUtilisateur;
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

        public String getSalle() {
            return salle;
        }

        public void setSalle(String salle) {
            this.salle = salle;
        }

        public String getRefUtilisateur() {
            return RefUtilisateur;
        }

        public void setRefUtilisateur(String RefUtilisateur) {
            this.RefUtilisateur = RefUtilisateur;
        }
    }


