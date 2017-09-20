package com.malens.opt.UI;/**
 * Created by malens on 2017-09-15.
 */

import com.malens.opt.Parser;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    public static class setName {
        public SimpleStringProperty name;

        public setName(SimpleStringProperty name) {
            this.name = name;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }
    }



    private Group setUpChrono()
    {

        GridPane chronoPane = new GridPane();
        TableView<setName> includedSets = new TableView<>();
        includedSets.setEditable(true);
        TableColumn selectedColumn = new TableColumn("Selected stat combos");
        selectedColumn.setCellValueFactory(
                new PropertyValueFactory<setName, String>("name")
        );
        selectedColumn.setEditable(false);
        selectedColumn.setPrefWidth(200);

        includedSets.getColumns().add(selectedColumn);
        includedSets.setFixedCellSize(25);
        includedSets.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ChoiceBox<String> possibleSets = new ChoiceBox<>(FXCollections.observableArrayList(
           "Berserker", "Commander", "Assassin"
        ));
        possibleSets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) return;
                includedSets.getItems().add(new setName(new SimpleStringProperty(newValue)));
                possibleSets.getItems().remove(newValue);
            }
        });
        chronoPane.add(possibleSets, 0, 1);
        chronoPane.add(includedSets, 0, 2);

        CheckBox isRune = new CheckBox("Include runes");
        CheckBox isSigil = new CheckBox("Include sigils");
        CheckBox isFood = new CheckBox("Include food");
        CheckBox isUtil = new CheckBox("Include utility");
        Pane checkBoxes = new Pane(isRune, isSigil, isFood, isUtil);
        VBox all = new VBox ();
        all.setVisible(true);
        all.setSpacing(5);
        all.setPadding(new Insets(10,0,0,10));

        Button start = new Button("Start sim");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parser parser = new Parser();
                String args[] = new String[10];
                args[0] = "-i ";
                for (setName x:includedSets.getItems()){
                    args[0] +=x.getName()+" ";
                }

                parser.parse(args);

            }
        });

        all.getChildren().add(start);
        all.getChildren().add(checkBoxes);
        all.getChildren().add(chronoPane);



        return new Group(all);
    }

    private Group setUpCondi()
    {
        VBox condiPane = new VBox();
        condiPane.getChildren().add(new TextField("dsa"));
        return new Group(condiPane);
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
        ArrayList<Group> grids = new ArrayList<>();
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
