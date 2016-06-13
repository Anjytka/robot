package npp.robot.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import npp.robot.core.Place;
import npp.robot.services.DrawPlaceService;
import npp.robot.services.GroupingService;
import npp.robot.windows.ContainersTableWindow;
import npp.robot.windows.MainWindow;

import java.net.URL;
import java.util.*;

/**
 * Created by mac on 28.05.16.
 */
public class PlaceController extends BaseController implements Initializable {

    public static String URL_FXML = "/fxml/place.fxml";
    private static Place place = Place.getInstance();

    private boolean isInitialized = false;

    @FXML private Pane allCells;

    @FXML private Pane preview;

    @FXML private Button makeStep;

    @FXML private Button doAll;

    @FXML private Button stop;

    @FXML private Label moveCount;

    @FXML private Button generate;

    private Thread thread;
    private volatile boolean stopped = false;

    private boolean ended = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        redrawAllCells();
        MainWindow.getNavigation().getStage().setTitle("Обработка данных");
        stop.setDisable(true);
        generate.setDisable(true);
    }

    public void doAll() {
        if (!isInitialized) {
            makeInitialization();
            isInitialized = true;
        }
        doAll.setDisable(true);
        stop.setDisable(false);
        GroupingService groupService = new GroupingService();
        thread = new Thread(() -> {
            while (!ended && !stopped && !Thread.currentThread().isInterrupted()) {
                GroupingService.ResultData resultData = groupService.startGrouping();
                ended = resultData.ended;
                Platform.runLater(() -> {
                    if (ended) {
                        generate.setDisable(false);
                        stop.setDisable(true);
                    }
                    moveCount.setText(String.valueOf(Integer.valueOf(moveCount.getText()) + resultData.movesCount));
                    redrawAllCells();
                    redrawPreview();
                });
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.error("{}", e.getMessage());
                }
            }
        });
        ended = false;
        stopped = false;
        thread.start();
    }

    public void stop() {
        stopped = true;
        try {
            thread.join();
            doAll.setDisable(false);
            makeStep.setDisable(false);
            stop.setDisable(true);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage());
        }
    }

    public void makeStep() {
        if (!isInitialized) {
            makeInitialization();
            isInitialized = true;
        }
        doAll.setDisable(false);
        stop.setDisable(true);
        GroupingService groupService = new GroupingService();
        GroupingService.ResultData resultData = groupService.startGrouping();
        redrawAllCells();
        redrawPreview();
        if (ended) {
            generate.setDisable(false);
        }
        moveCount.setText(String.valueOf(Integer.valueOf(moveCount.getText()) + resultData.movesCount));
    }

    public void previewContainers() {
        ContainersTableWindow containersWindow = new ContainersTableWindow();
        containersWindow.initOwner(MainWindow.getNavigation().getStage());
        containersWindow.show();
    }

    public void generate() {
        place.clear();
        MainWindow.getNavigation().getStage().setTitle("Входные данные");
        MainWindow.getNavigation().goBack(350, 510);
    }

    private void makeInitialization() {
        place.getCells().sort((o1, o2) -> Double.compare(o2.getRadiation(), o1.getRadiation()));
        place.setCurrentCell(place.getCells().get(0));
        redrawPreview();
    }

    private void redrawAllCells() {
        Canvas canvas;
        if (allCells.getChildren().size() == 0 ){
            canvas = new Canvas(500, 610);
            allCells.getChildren().add( canvas );
        } else {
            canvas = (Canvas) allCells.getChildren().get(0);
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 500, 610);
        DrawPlaceService.drawCells(gc);
    }

    private void redrawPreview() {
        Canvas canvas;
        if (preview.getChildren().size() == 0) {
            canvas = new Canvas(180, 240);
            preview.getChildren().add( canvas );
        } else {
            canvas = (Canvas) preview.getChildren().get(0);
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 180, 240);
        DrawPlaceService.drawUnitsOfCell(gc, place.getCurrentCell(), 0, 240, 6, true);
        redrawSelectedCell();
    }

    private void redrawSelectedCell() {
        Canvas allCellsCanvas;
        if ( allCells.getChildren().get(0) instanceof Canvas) {
            allCellsCanvas = (Canvas) allCells.getChildren().get(0);
            GraphicsContext allCellsContext = allCellsCanvas.getGraphicsContext2D();
            DrawPlaceService.markSelectedCell(allCellsContext, place.getCurrentCell());
        }
    }
}
