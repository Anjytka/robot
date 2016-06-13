package npp.robot.windows;

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
import npp.robot.models.Unit;

/**
 * Created by mac on 13.06.16.
 */
public class UnitsTableWindow extends Stage {

    public static Place place = Place.getInstance();

    public UnitsTableWindow(int containerId) {
        this.setTitle("Элементы контейнера");

        TableView<Unit> unitsTable = new TableView<>();
        unitsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Unit, String> col = new TableColumn<>("Радиоактивность");
        col.setMinWidth(200);
        col.setCellValueFactory(new PropertyValueFactory<>("radiation"));
        unitsTable.getColumns().add(col);

        col = new TableColumn<>("Длина");
        col.setMinWidth(200);
        col.setCellValueFactory(new PropertyValueFactory<>("length"));
        unitsTable.getColumns().add(col);

        unitsTable.setItems(FXCollections.observableList(place.getContainers().get(containerId).getUnits()));

        VBox vBox = new VBox();
        vBox.getChildren().add(unitsTable);
        VBox.setVgrow(unitsTable, Priority.ALWAYS);
        Scene scene = new Scene(vBox);
        this.setScene(scene);
        this.sizeToScene();
    }
}
