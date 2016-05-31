package npp.robot.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import npp.robot.core.BaseController;
import npp.robot.core.Place;
import npp.robot.models.Cell;
import npp.robot.services.DrawPlaceService;
import npp.robot.windows.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;

/**
 * Created by mac on 28.05.16.
 */
public class PlaceController extends BaseController implements Initializable {

    public static String URL_FXML = "/fxml/place.fxml";
    private static Place place = Place.getInstance();

    @FXML private Pane allCells;

    @FXML private Pane preview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Canvas canvas = new Canvas(500, 600);
        allCells.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        DrawPlaceService.drawCells(gc);
    }

    public void startGrouping() {
        place.getCells().sort((o1, o2) -> Double.compare(o2.getRadiation(), o1.getRadiation()));
        Cell current = place.getCells().get(0);
        log.debug("{}",current.getRadiation());

        Canvas canvas = new Canvas(180, 240);
        preview.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        DrawPlaceService.drawUnitsOfCell(gc, current, 0, 240, 6);
        Canvas allCellsCanvas;
        if ( allCells.getChildren().get(0) instanceof Canvas) {
            allCellsCanvas = (Canvas) allCells.getChildren().get(0);
            GraphicsContext allCellsContext = allCellsCanvas.getGraphicsContext2D();
            DrawPlaceService.markSelectedCell(allCellsContext, current);
        }
    }
}
