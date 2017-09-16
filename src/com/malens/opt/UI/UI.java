package com.malens.opt.UI;/**
 * Created by malens on 2017-09-15.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private ArrayList<GridPane> grids = new ArrayList<>();

    private int width = 800, height = 600;
    private ChoiceBox<String> modeBox;

    public ChoiceBox<String> getModeBox() {
        return modeBox;
    }

    private GridPane setUpChrono()
    {
        GridPane chronoPane = new GridPane();
        chronoPane.add(new TextField("asd"), 0, 0);
        chronoPane.add(new TextField("asd"), 1, 0);
        chronoPane.add(new TextField("asd"), 2, 0);
        return chronoPane;
    }

    private GridPane setUpCondi()
    {
        GridPane condiPane = new GridPane();
        condiPane.add(new TextField("dsa"), 0, 0);
        return condiPane;
    }

    private void setScene (int oldval, int newval){
        if (oldval<0) {
            if (newval<grids.size()){
                grids.get(newval).setVisible(true);
            }
            return;
        }
        if (oldval<grids.size()&&newval<grids.size()){
            grids.get(newval).setVisible(true);
            grids.get(oldval).setVisible(false);
        }
    }

    private ChoiceBox<String> createModeBox(Stage stage)
    {

        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "Chrono", "Condi"
        ));
        choiceBox.setMinWidth(200);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setScene(oldValue.intValue(), newValue.intValue());
                stage.sizeToScene();
            }
        });
        this.modeBox = choiceBox;
        return choiceBox;
    }

    private TextField test = new TextField("test");
    @Override
    public void start(Stage primaryStage) {
        grids = new ArrayList<GridPane>();
        grids.add(setUpChrono());
        grids.add(setUpCondi());


        Group primaryGroup = new Group(grids.get(0), grids.get(1));
        GridPane pane = new GridPane();
        pane.add(createModeBox(primaryStage), 0, 0);
        pane.add(primaryGroup, 0, 1);
        primaryStage.setScene(new Scene(pane));

        grids.get(0).setVisible(false);
        grids.get(1).setVisible(false);

        primaryStage.setResizable(true);
        primaryStage.setTitle("gw2optitool");
        primaryStage.show();

    }
}
