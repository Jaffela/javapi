package org.example.jdoula;

import Service.reservationService;
import entite.reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class cardres {
    reservationService rs = new reservationService();
    private reservation reservation;

    @FXML
    private Label id_debut;

    @FXML
    private Label id_fin;

    @FXML
    private HBox id_resbox;

    @FXML
    private Label id_service;

    @FXML
    private Label id_statut;

    private reservation re;
    public void setReservation (reservation re) {
        this.re = re;
        id_statut.setText(this.re.getStatut());
        id_debut.setText(this.re.getDebut().toString());
        id_fin.setText(this.re.getFin().toString());
        id_service.setText(this.re.getService());


    }


    public void setData(reservation reservation) {
        this.reservation = reservation;
        id_statut.setText(reservation.getStatut());
        id_debut.setText(reservation.getDebut().toString());
        id_fin.setText(reservation.getFin().toString());
        id_service.setText(reservation.getService());
        id_resbox.setStyle("-fx-background-color: #E5E5E5; /* Background color */\n" +
                "    -fx-background-radius: 20; /* Rounded corners */\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 10);");


    }
    @FXML
    void suppres(ActionEvent event) {
        if (reservation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette réservation ?");
            alert.setContentText("Cette action est irréversible.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer la carte de l'affichage principal (cardlyout)

                // Supprimer le produit de la base de données
                rs.delete1(reservation.getId()); // Supposer que vous avez une méthode pour supprimer le produit dans ServicesProduit

                // Afficher un message de confirmation
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Suppression réussie");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("La réservation a été supprimée avec succès.");
                confirmationAlert.showAndWait();
            }
        } else {
            // Gérer le cas où l'objet reservation est null
            System.out.println("Erreur : Impossible de supprimer une réservation null.");
        }
    }
    @FXML
    void redirect(ActionEvent event) {
        try {
            // Obtenez la catégorie associée à cet élément
            reservation data = reservation;

            // Vérifiez si la catégorie est null
            if (data != null) {
                // Affichez l'ID de la catégorie sélectionnée
                System.out.println("ID de la catégorie sélectionnée : " + data.getId());

                // Chargez la vue de modification de la catégorie
                FXMLLoader loader = new FXMLLoader(getClass().getResource("updateres.fxml"));
                Parent root = loader.load();
                updateres controller = loader.getController();

                controller.setReservation(data);
                Scene scene = new Scene(root);
                Stage stage = new Stage();// Créez une nouvelle fenêtre pour la vue de modification
                stage.setScene(scene);
                stage.setTitle("modifier Categorie");
                stage.show();
            } else {
                // Si la catégorie est null, affichez un message d'erreur ou gérez-le selon votre cas d'utilisation
                System.out.println("La reservation sélectionnée est null.");
            }
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de la vue de modification : " + ex.getMessage());
        }
    }



}

