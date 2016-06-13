package npp.robot.windows;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import npp.robot.controllers.InputDataController;
import npp.robot.core.Navigation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindow extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainWindow.class);
    private static Navigation navigation;
    public static Navigation getNavigation()
    {
        return navigation;
    }

    @FXML Canvas place2;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        navigation = new Navigation(stage, 350, 510);

        stage.setTitle("Входные данные");
        stage.show();

        MainWindow.getNavigation().load(InputDataController.URL_FXML).show();
    }
}
