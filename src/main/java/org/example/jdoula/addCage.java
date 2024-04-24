package org.example.jdoula;

import Service.cageService;
import entite.cage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label; // Importer Label
import javafx.stage.Stage;
import java.io.IOException;

public class addCage {

    @FXML
    private Button id_add;

    @FXML
    private Button id_show;

    @FXML
    private ChoiceBox<String> id_type;

    @FXML
    private ChoiceBox<String> id_emp;

    @FXML
    private Label typeErrorLabel; // Ajouter un label pour afficher l'erreur de type

    @FXML
    private Label emplacementErrorLabel; // Ajouter un label pour afficher l'erreur d'emplacement

    @FXML
    void show(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("showallcage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void add() {
        cageService rs = new cageService();
        String type = id_type.getValue();
        String emplacement = id_emp.getValue();

        if (type == null || type.isEmpty()) { // Vérifier si le champ de type est vide
            typeErrorLabel.setVisible(true); // Afficher le label d'erreur de type
            return; // Sortir de la méthode sans ajouter la cage
        } else {
            typeErrorLabel.setVisible(false); // Cacher le label d'erreur de type s'il n'est pas vide
        }

        if (emplacement == null || emplacement.isEmpty()) { // Vérifier si le champ d'emplacement est vide
            emplacementErrorLabel.setVisible(true); // Afficher le label d'erreur d'emplacement
            return; // Sortir de la méthode sans ajouter la cage
        } else {
            emplacementErrorLabel.setVisible(false); // Cacher le label d'erreur d'emplacement s'il n'est pas vide
        }

        cage newcage = new cage(type, emplacement);
        rs.add(newcage);

        System.out.println("Cage ajoutée avec succès !");
    }
}
