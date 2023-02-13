package modele;

public class FicheFourniture {

        private int id_fiche_fourniture;
        private String nom;
        private double prix;
        private int quantite;

        public FicheFourniture(int id_fiche_fourniture, String nom, double prix, int quantite) {
            this.id_fiche_fourniture = id_fiche_fourniture;
            this.nom = nom;
            this.prix = prix;
            this.quantite = quantite;
        }

    public int getId_fiche_fourniture() {
        return id_fiche_fourniture;
    }

    public void setId_fiche_fourniture(int id_fiche_fourniture) {
        this.id_fiche_fourniture = id_fiche_fourniture;
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






