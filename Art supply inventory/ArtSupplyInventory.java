package artsupplycore;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Collections;

import static java.lang.System.out;

public class ArtSupplyInventory extends Application {

    private final static ObservableList<String> list = FXCollections.observableArrayList();
    private static ListView<String> view;
    private static ComboBox category;
    private String line;

    public void setFileToRead(String fileName) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            while((line = br.readLine()) != null) {
                list.add(line);
            }
            Collections.sort(list);

        } catch (Exception exception) {}
    }

    public ObservableList<String> getItemFrom(String itemType) {
        if (itemType == "paints") {
            setFileToRead("paints.csv");
        }
        if (itemType == "pencils") {
            setFileToRead("pencils.csv");
        }
        return list;
    }

    @Override
    public void init() throws Exception {
        out.println("Window launched");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Text rightContent = new Text("");
        Text bottomContent = new Text("");
        Text leftContent = new Text("");

        Button searchButton = new Button("Search");

        category = new ComboBox();
        category.setMaxWidth(300);
        category.getItems().add("paints");
        category.getItems().add("pencils");
        category.setPromptText("Type");

//        category.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                out.println((category.getValue()).toString());
//                getItemFrom((category.getValue()).toString());
//            }
//        });

        TextField searchInput = new TextField();


        HBox searchBar = new HBox(); // top
        searchBar.setPadding(new Insets(5));
        searchBar.setAlignment(Pos.CENTER);
        searchBar.getChildren().addAll(category, searchInput, searchButton);

        view = new ListView<>();
        view.getItems().addAll(this.list);

        VBox listBox = new VBox(); // center
        listBox.setMaxWidth(320);
        listBox.getChildren().addAll(view);

        BorderPane.setAlignment(listBox, Pos.CENTER);
        BorderPane.setAlignment(searchBar, Pos.TOP_CENTER);
        BorderPane.setAlignment(rightContent, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(bottomContent, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(leftContent, Pos.CENTER_LEFT);

        // BorderPane order of parameters: center top right bottom left
        BorderPane contents = new BorderPane(listBox, searchBar, rightContent, bottomContent, leftContent);
        contents.setPrefSize(400,400);

        Scene scene = new Scene(contents);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Art Supplies");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        out.println("Window closed.");
    }

    public static void main(String[] args) {
        ArtSupplyInventory search = new ArtSupplyInventory();
        search.getItemFrom("paints");
        launch(args);
    }
}