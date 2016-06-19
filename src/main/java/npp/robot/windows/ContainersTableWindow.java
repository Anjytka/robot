package npp.robot.windows;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import npp.robot.core.Place;
import npp.robot.models.Container;


public class ContainersTableWindow extends Stage {

    public static Place place = Place.getInstance();

    public ContainersTableWindow() {
        this.setTitle("Контейнеры");

        TableView<Container> containersTable = new TableView<>();
        containersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Container, String> col = new TableColumn<>("Радиоактивность, у.е.");
        col.setMinWidth(200);
        col.setCellValueFactory(new PropertyValueFactory<>("radiation"));
        containersTable.getColumns().add(col);

        col = new TableColumn<>("Свободный объем, %");
        col.setMinWidth(200);
        col.setCellValueFactory(new PropertyValueFactory<>("restFullnessInPercents"));
        containersTable.getColumns().add(col);

        col = new TableColumn<>();
        col.setMinWidth(200);
        col.setCellFactory(new Callback<TableColumn<Container, String>, TableCell<Container, String>>() {
            @Override
            public TableCell<Container, String> call(TableColumn<Container, String> param) {
                return new TableCell<Container, String>() {
                    private Button btn = new Button();
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                UnitsTableWindow unitsTableWindow = new UnitsTableWindow(getIndex());
                                unitsTableWindow.show();
                            });
                            btn.setText("Просмотр элементов");
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        });
        containersTable.getColumns().add(col);
        containersTable.setItems(FXCollections.observableList(place.getContainers()));

        VBox vBox = new VBox();
        vBox.getChildren().add(containersTable);
        VBox.setVgrow(containersTable, Priority.ALWAYS);
        Scene scene = new Scene(vBox);
        this.setScene(scene);
        this.sizeToScene();
    }
}
