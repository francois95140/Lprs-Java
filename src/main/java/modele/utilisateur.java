package modele;

public class utilisateur {



        private String prenom;
        private String nom;
        private String email;
        private String role;

        public utilisateur(String prenom, String nom, String email, String role) {
            this.prenom = prenom;
            this.nom = nom;
            this.email = email;
            this.role = role;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }








