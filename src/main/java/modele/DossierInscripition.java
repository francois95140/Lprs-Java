package modele;

public class DossierInscripition {

        private int id_dossier;
        private String date;
        private String heure;
        private String filiere;
        private String motivation;
        private String ref_fiche;

        public DossierInscripition(int id_dossier, String date, String heure, String filiere, String motivation, String ref_fiche) {
            this.id_dossier = id_dossier;
            this.date = date;
            this.heure = heure;
            this.filiere = filiere;
            this.motivation = motivation;
            this.ref_fiche = ref_fiche;
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

        public String getRef_fiche() {
            return ref_fiche;
        }

        public void setRef_fiche(String ref_fiche) {
            this.ref_fiche = ref_fiche;
        }
    }









