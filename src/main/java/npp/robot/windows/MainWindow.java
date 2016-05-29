package npp.robot.windows;

import javafx.application.Application;
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

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        navigation = new Navigation(stage, 350, 500);

        stage.setTitle("Входные данные");
        stage.show();

        //navigate to first view
        MainWindow.getNavigation().load(InputDataController.URL_FXML).show();

//        log.info("Starting Hello JavaFX and Maven demonstration application");
//
//        String fxmlFile = "/fxml/input_data.fxml";
//        log.debug("Loading FXML for main view from: {}", fxmlFile);
//        InputDataController ctrlGenerate = new InputDataController();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setController(ctrlGenerate);
//        Parent root = (Parent) loader.load(getClass().getResource(fxmlFile));
//        log.debug("Showing JFX scene");
//
//        Scene scene = new Scene(root, 350, 550);
//        scene.getStylesheets().add("/styles/styles.css");
//
//        stage.setTitle("Входные данные");
//        stage.setScene(scene);


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


//        stage.show();
    }
}
