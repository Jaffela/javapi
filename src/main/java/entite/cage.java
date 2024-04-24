package entite;


public class cage {
    private int id;
    private String type;
    private Boolean disponibilite;
    private String emplacement;

    public cage( int id,String type, Boolean disponibilite, String emplacement) {
        this.type = type;
        this.id = id;
        this.disponibilite = disponibilite;
        this.emplacement = emplacement;
    }

    public cage(String type, String emplacement) {
        this.type = type;

        this.emplacement = emplacement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "cage{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", disponibilite=" + disponibilite +
                ", emplacement='" + emplacement + '\'' +
                '}';
    }
}
