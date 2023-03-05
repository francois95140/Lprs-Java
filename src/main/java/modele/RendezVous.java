package modele;

public class RendezVous {

        private int id_RendezVous;

        private String date;
        private String heure;
        private String salle;
        private String ref_prof;
        private int ref_dossier;




    public RendezVous(int id_rdv, String date, String heure, String salle, String ref_prof, int ref_dossier) {
            this.id_RendezVous = id_rdv;
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

        public String getSalle() {
            return salle;
        }

        public void setSalle(String salle) {
            this.salle = salle;
        }

        public String getref_prof() {
            return ref_prof;
        }

        public void setref_prof(String ref_prof) {
            this.ref_prof = ref_prof;
        }
         public int getref_dossier() {
         return ref_dossier;
    }

         public void setref_dossier(int ref_dossier) {
           ref_dossier = ref_dossier;
    }

    }


