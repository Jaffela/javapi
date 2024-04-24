package org.example.jdoula;

import Service.cageService;
import entite.cage;
import entite.reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class cardcag {

    cageService service = new cageService();
    private cage cage;

    @FXML
    private Button id_del;

    @FXML
    private Label id_dis;

    @FXML
    private Label id_emp;

    @FXML
    private HBox id_resbox;

    @FXML
    private Label id_typ;

    @FXML
    private Button id_update;

    private cage ce;
    public void setCage (cage ce) {
        this.ce = ce;
        id_typ.setText(this.ce.getType());
        id_emp.setText(this.ce.getEmplacement());
        if(this.ce.getDisponibilite().equals(Boolean.TRUE)){
            id_dis.setText("diponible");

        }else id_dis.setText("non diponible");





    }


    public void setData(cage cage) {
        this.cage = cage;
        id_typ.setText(cage.getType());
        id_emp.setText(cage.getEmplacement());
        id_dis.setText(cage.getDisponibilite().toString());
        id_resbox.setStyle("-fx-background-color: #E5E5E5; /* Background color */\n" +
                "    -fx-background-radius: 20; /* Rounded corners */\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 10);");


    }

    @FXML
    void redirect(ActionEvent event) {
        try {
            // Obtenez la catégorie associée à cet élément
            cage data = cage;

            // Vérifiez si la catégorie est null
            if (data != null) {
                // Affichez l'ID de la catégorie sélectionnée
                System.out.println("ID de la catégorie sélectionnée : " + data.getId());

                // Chargez la vue de modification de la catégorie
                FXMLLoader loader = new FXMLLoader(getClass().getResource("updatecag.fxml"));
                Parent root = loader.load();
                updatecag controller = loader.getController();

                controller.setcage(data);
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

    @FXML
    void suppres(ActionEvent event) {
        if (cage != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette réservation ?");
            alert.setContentText("Cette action est irréversible.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer la carte de l'affichage principal (cardlyout)

                // Supprimer le produit de la base de données
                service.delete2(cage.getId()); // Supposer que vous avez une méthode pour supprimer le produit dans ServicesProduit

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

}
