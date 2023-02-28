package modele;

public class RendezVous {

        private int id_RendezVous;

        private String date;
        private String heure;
        private String salle;
        private String RefUtilisateur;
        private int RefEleve;




    public RendezVous(int id_rdv, String date, String heure, String salle, String RefUtilisateur, int RefEleve) {
            this.id_RendezVous = id_rdv;
            this.date = date;
            this.heure = heure;
            this.salle = salle;
            this.RefUtilisateur = RefUtilisateur;
            this.RefEleve = RefEleve;
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

        public String getRefUtilisateur() {
            return RefUtilisateur;
        }

        public void setRefUtilisateur(String RefUtilisateur) {
            this.RefUtilisateur = RefUtilisateur;
        }
         public int getRefEleve() {
         return RefEleve;
    }

         public void setRefEleve(int refEleve) {
           RefEleve = refEleve;
    }

    }


