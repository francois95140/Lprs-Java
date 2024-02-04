package modele;

public class Salle {

    int idSalle;
    int numerot;
    String batiment;

    public Salle(int idSalle, int numerot, String batiment) {
        this.idSalle = idSalle;
        this.numerot = numerot;
        this.batiment = batiment;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public int getNumerot() {
        return numerot;
    }

    public String getBatiment() {
        return batiment;
    }
}
