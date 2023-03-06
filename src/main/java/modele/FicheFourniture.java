package modele;

public class FicheFourniture {

        private int id_fiche_fourniture;
        private int ref_fournisseur;
        private double prix;
        private int ref_fourniture;

        public FicheFourniture(int id_fiche_fourniture, int ref_fournisseur, double prix, int ref_fourniture) {
            this.id_fiche_fourniture = id_fiche_fourniture;
            this.ref_fourniture=ref_fourniture;
            this.prix = prix;
            this.ref_fournisseur=ref_fournisseur;
        }

    public int getId_fiche_fourniture() {
        return id_fiche_fourniture;
    }

    public void setId_fiche_fourniture(int id_fiche_fourniture) {
        this.id_fiche_fourniture = id_fiche_fourniture;
    }


        public double getprix() {
            return prix;
        }

        public void setprix(double prix) {
            this.prix = prix;
        }

    public int getRef_fournisseur() {
        return ref_fournisseur;
    }

    public void setRef_fournisseur(int ref_fournisseur) {
        this.ref_fournisseur = ref_fournisseur;
    }

    public int getRef_fourniture() {
        return ref_fourniture;
    }

    public void setRef_fourniture(int ref_fourniture) {
        this.ref_fourniture = ref_fourniture;
    }
}






