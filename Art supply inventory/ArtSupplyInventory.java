import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Collections;

import static java.lang.System.out;

public class ArtSupplyInventory extends Application {

    private final static ObservableList<String> list = FXCollections.observableArrayList();
    private ListView<String> view;
    private static ComboBox category;
    private String line, selectedCategory, fileName, listSearch, listSearchSub;

    @Override
    public void init() throws Exception {
        out.println("Window launched");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Listview object must be instantiated here for the list to populate correctly by the combobox listener
        // Credits to Scott LeTourneau for helping me debug this line and view.setItems(list)!
        view = new ListView<>();

        Text rightContent = new Text("");
        Text leftContent = new Text("");


        category = new ComboBox();
        category.setMaxWidth(300);
        category.getItems().add("paints");
        category.getItems().add("pencils");
        category.setPromptText("Type");

        category.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue <? extends String> observable, String oldValue, String newValue) {
                view.getItems().clear();

                selectedCategory = newValue;
                out.println(selectedCategory); // prints the selected option from combobox

                if (selectedCategory == "paints") {
                    fileName = "paints.csv";
                }
                else if (selectedCategory == "pencils") {
                    fileName = "pencils.csv";
                }

                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                    while((line = br.readLine()) != null) {
                        list.add(line);
                    }
                    Collections.sort(list);

                    view.setItems(list); // view.getItems().add() does not work here

                } catch (Exception exception) {}
            }
        });

        TextField searchInput = new TextField();
        searchInput.setPromptText("Input keyword");
        searchInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<String> newList = FXCollections.observableArrayList();

                for (int i = 0; i < list.size(); i++) {
                    listSearch = list.get(i);

                    for (int j = 0; j < listSearch.length()-newValue.length() + 1; j++) {
                        String test0 = (newValue.toUpperCase());
                        String test1 =  (listSearch.substring(j, j + newValue.length())).toUpperCase();
                        if (test0.equals(test1)) {
                            newList.add(list.get(i));
                            out.println("Found matching item(s)");
                            break;
                        }
                    }
                }

                out.println(newList);
                view.setItems(newList);
            }
        });

        HBox searchBar = new HBox(); // top
        searchBar.setPadding(new Insets(5));
        searchBar.setAlignment(Pos.CENTER);
        searchBar.getChildren().addAll(category, searchInput);

        VBox listBox = new VBox(); // center
        listBox.setPadding(new Insets(5));
        listBox.setMaxWidth(300);
        listBox.getChildren().addAll(view);

        Text steps = new Text("Directions:\n\n1. Select type\n2. Scroll or filter-search by item keyword.\n\nWelley Loc\nVersion 1 (released 8/27/19)\n");
        steps.setWrappingWidth(280);
        HBox directions = new HBox();
        directions.setAlignment(Pos.CENTER);
        directions.setPadding(new Insets(5));
        directions.getChildren().addAll(steps);

        BorderPane.setAlignment(listBox, Pos.CENTER);
        BorderPane.setAlignment(searchBar, Pos.TOP_CENTER);
        BorderPane.setAlignment(rightContent, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(directions, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(leftContent, Pos.CENTER_LEFT);

        // BorderPane order of parameters: center top right bottom left
        BorderPane contents = new BorderPane(listBox, searchBar, rightContent, directions, leftContent);
        contents.setPrefSize(380,400);

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
        launch(args);
    }
}