package npp.robot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import npp.robot.controllers.InputDataHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/input_data.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        InputDataHandlerController ctrlGenerate = new InputDataHandlerController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(ctrlGenerate);
        Parent root = (Parent) loader.load(getClass().getResource(fxmlFile));
        log.debug("Showing JFX scene");

        Scene scene = new Scene(root, 350, 550);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Входные данные");
        stage.setScene(scene);


//        Canvas canvas = new Canvas( 400, 200 );
//        root.getChildren().add( canvas );
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill( Color.RED );
//        gc.setStroke( Color.BLACK );
//        gc.setLineWidth(2);
//        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
//        gc.setFont( theFont );
//        gc.fillText( "Hello, World!", 60, 50 );
//        gc.strokeText( "Hello, World!", 60, 50 );


        stage.show();
    }
}
