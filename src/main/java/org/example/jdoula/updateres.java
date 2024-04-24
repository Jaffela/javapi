package org.example.jdoula;

import Service.reservationService;
import entite.reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

public class updateres {
    reservationService rs = new reservationService();
    private reservation reservation;

    @FXML
    private DatePicker id_dated;

    @FXML
    private DatePicker id_datef;

    @FXML
    private TextField id_service;

    @FXML
    private TextField id_statut;

    @FXML
    private Button id_update;

    private reservation re;

    public void setReservation(reservation re) {
        this.re = re;
        id_statut.setText(this.re.getStatut());
        id_dated.setValue(this.re.getDebut().toLocalDate()); // Use setValue to set the date
        id_datef.setValue(this.re.getFin().toLocalDate());   // Use setValue to set the date
        id_service.setText(this.re.getService());
    }


    @FXML
    void updateres(ActionEvent event) {
        if (re == null) {
            return;
        }
        String statut = id_statut.getText();
        Date dated = Date.valueOf(id_dated.getValue());
        Date datef = Date.valueOf(id_datef.getValue());
        String service = id_service.getText();
        if (statut.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you must input the Statut");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            // Mettre à jour les champs de l'objet réservation existant
            re.setStatut(statut);
            re.setDebut(dated);
            re.setFin(datef);
            re.setService(service);

            // Appeler la méthode update() du service de réservation pour mettre à jour la base de données
            rs.update(re);

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reservation updated successfully.");
            alert.setHeaderText(null);
            alert.show();
        }
    }

}
