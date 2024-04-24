package entite;

import java.sql.Date;

public class reservation {
    private int id;
    private String statut;
    private Date dated;
    private Date datef;
    private String service;

    public reservation(int id, String statut, Date dated, Date datef, String service) {
        this.id = id;
        this.statut = statut;
        this.dated = dated;
        this.datef = datef;
        this.service = service;
    }

    public reservation(Date dated, Date datef, String service) {
        this.statut = statut;
        this.dated = dated;
        this.datef = datef;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public String getStatut() {
        return statut;
    }

    public Date getDebut() {
        return dated;
    }

    public Date getFin() {
        return datef;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setFin(Date fin) {
        this.datef = fin;
    }

    public void setDebut(Date debut) {
        this.dated = debut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "reservation{" +
                "id=" + id +
                ", statut='" + statut + '\'' +
                ", debut=" + dated +
                ", fin=" + datef +
                ", service='" + service + '\'' +
                '}';
    }
}
