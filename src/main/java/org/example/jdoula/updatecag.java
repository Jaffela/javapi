package org.example.jdoula;

import Service.cageService;
import entite.cage;
import entite.reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Date;

public class updatecag {
    cageService cs = new cageService();
    private cage cage;

    @FXML
    private TextField id_emp;

    @FXML
    private TextField id_typ;

    @FXML
    private Button id_update;

    private cage ca;

    public void setcage(cage ca) {
        this.ca = ca;
        id_typ.setText(this.ca.getType());
        id_emp.setText(this.ca.getEmplacement());
    }

    @FXML
    void updatecag(ActionEvent event) {
        if (ca == null) {
            return;
        }
        String type = id_typ.getText();
        String emplacement = id_emp.getText();
        if (type.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you must input the Statut");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            // Mettre à jour les champs de l'objet réservation existant
            ca.setType(type);
            ca.setEmplacement(emplacement);

            // Appeler la méthode update() du service de réservation pour mettre à jour la base de données
            cs.update(ca);

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation updated successfully.");
            alert.setHeaderText(null);
            alert.show();
        }

    }

}
