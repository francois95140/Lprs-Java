package modele;

public class Logs {
    private int id_logs;
    private int ref_compte;
    private String adresse_ip;
    private String date;

    public Logs(int ref_compte, String adresse_ip) {
        this.ref_compte = ref_compte;
        this.adresse_ip = adresse_ip;
    }

    public Logs(int id_logs, int ref_compte, String adresse_ip, String date) {
        this.id_logs = id_logs;
        this.ref_compte = ref_compte;
        this.adresse_ip = adresse_ip;
        this.date = date;
    }

    public int getId_logs() {
        return id_logs;
    }

    public int getRef_compte() {
        return ref_compte;
    }

    public String getAdresse_ip() {
        return adresse_ip;
    }

    public String getDate() {
        return date;
    }

    public void setRef_compte(int ref_compte) {
        this.ref_compte = ref_compte;
    }

    public void setAdresse_ip(String adresse_ip) {
        this.adresse_ip = adresse_ip;
    }
}
