package appli.User;

public class FicheFourniture {


        private String nom;
        private double prix;
        private int quantite;

        public FicheFourniture(String nom, double prix, int quantite) {
            this.nom = nom;
            this.prix = prix;
            this.quantite = quantite;
        }

        public String getnom() {
            return nom;
        }

        public void setnom(String nom) {
            this.nom = nom;
        }

        public double getprix() {
            return prix;
        }

        public void setprix(double prix) {
            this.prix = prix;
        }

        public int getquantite() {
            return quantite;
        }

        public void setquantite(int quantite) {
            this.quantite = quantite;
        }
    }






