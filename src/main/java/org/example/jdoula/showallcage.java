package org.example.jdoula;

import Service.cageService;
import entite.cage;
import entite.reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class showallcage implements Initializable {

    private cage cage;
    cageService cs = new cageService();

    @FXML
    private FlowPane cardlayout;

    @FXML
    private AnchorPane id_anchor;

    @FXML
    private Button id_reload;

    @FXML
    void reload_page(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addCage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<cage> list = cs.getAll();
        cardlayout.toFront();
        cardlayout.setHgap(20);
        cardlayout.setVgap(20);

        if (list.isEmpty()) {
            System.out.println("La liste des reservation est vide.");
        } else {
            System.out.println("Nombre de produits récupérés depuis la base de données : " + list.size());
            for (cage cage : list){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cardcag.fxml"));
                    Pane cardView = loader.load();
                    cardcag controller = loader.getController();
                    controller.setData(cage); // Appel de la méthode setData
                    cardlayout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
