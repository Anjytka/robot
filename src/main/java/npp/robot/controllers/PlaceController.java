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
import npp.robot.services.DrawPlaceService;
import npp.robot.windows.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mac on 28.05.16.
 */
public class PlaceController extends BaseController implements Initializable {

    public static String URL_FXML = "/fxml/place.fxml";

//    @FXML private Canvas place;
//    @FXML private Group group;
    @FXML private Pane group;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Canvas canvas = new Canvas(500, 600);
        group.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill( Color.RED );
//        gc.setStroke( Color.BLACK );
//        gc.setLineWidth(2);
//        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
//        gc.setFont( theFont );
//        gc.fillText( "Hello, World!", 60, 50 );
//        gc.strokeText( "Hello, World!", 60, 50 );
        DrawPlaceService.drawCells(gc);

    }
}
