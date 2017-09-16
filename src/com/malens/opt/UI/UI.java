package com.malens.opt.UI;/**
 * Created by malens on 2017-09-15.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UI extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {



        primaryStage.setResizable(false);
        primaryStage.setTitle("gw2optitool");
        GridPane pane = new GridPane();
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(
           "Chrono", "Condi"
        ));
        choiceBox.setMinWidth(200);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hideUI(oldValue);
                showUI(newValue);
            }
        });
        pane.getChildren().add(choiceBox);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

    }
}
