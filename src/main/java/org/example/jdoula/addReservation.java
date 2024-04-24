package org.example.jdoula;

import Service.reservationService;
import entite.reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;


public class addReservation {

    @FXML
    private DatePicker id_dated;

    @FXML
    private DatePicker id_datef;

    @FXML
    private TextField id_service;

    @FXML
    private TextField id_statue;
    @FXML
    private Button id_add;

    @FXML
    void afficherres(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("showAll.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void ajouterRes(ActionEvent event) {
        reservationService rs= new reservationService();
        Date dated = Date.valueOf(id_dated.getValue());
        Date datef = Date.valueOf(id_datef.getValue());
        String service = id_service.getText();

        // Create a new reservation object
        reservation newReservation = new reservation( dated, datef, service);

        // Add the reservation using the service
        rs.add(newReservation);

        // Optionally, you can provide feedback to the user
        System.out.println("Reservation added successfully!");
    }


}
