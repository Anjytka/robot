package npp.robot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/hello.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        GenerateDataController ctrlGenerate = new GenerateDataController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(ctrlGenerate);
        Parent root = (Parent) loader.load(getClass().getResource(fxmlFile));
        log.debug("Showing JFX scene");

        //root
//        BorderPane root = new BorderPane();
//
//        //menu bar
//        MenuBar menuBar = new MenuBar();
//        Menu file = new Menu("Файл");
//        MenuItem itmClear = new MenuItem("Очистить");
//        MenuItem itmClose = new MenuItem("Закрыть");
//
//        Menu help = new Menu("?");
//        MenuItem itmHelp = new MenuItem("Справка");
//
//
//        file.getItems().addAll(itmClear,itmClose);
//        help.getItems().addAll(itmHelp);
//
//        menuBar.getMenus().addAll(file,help);
//
//        root.setTop(menuBar);
//
//        //fields
//        GridPane grid = new GridPane();
//        grid.getStyleClass().add("main-panel");
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(10, 25, 10, 25));
//
//        VBox vbox = new VBox();
//        vbox.setSpacing(10);
//
//
//        Text header = new Text("Заполнение входных данных");
//
//        Label lblCount = new Label("Количество ячеек:");
//        TextField txtfCount = new TextField();
//        txtfCount.setId("fCount");
//        Label lblFullNorm = new Label("Нормальная заполненность контейнера:");
//        TextField txtfFullNorm = new TextField();
//        Label lblRNorm = new Label("Допустимая разница фона эл-та и контейнера:");
//        TextField txtfRNorm = new TextField();
//        Label lblPutBackDanger = new Label("Опасность опускания эл-та :");
//        TextField txtfPutBackDanger = new TextField();
//        Label lblMoveDanger = new Label("Опасность перемещения робота:");
//        TextField txtfMoveDanger = new TextField();
//        Label lblDistNorm = new Label("Приемлемая дистанция перемещения:");
//        TextField txtfDistNorm = new TextField();
//
//        Button btnStart = new Button("Запуск");
//
//        btnStart.setOnAction(e -> ctrlGenerate.dataHandler());
//
//        vbox.getChildren().addAll(header,
//                lblCount, txtfCount,
//                lblFullNorm, txtfFullNorm,
//                lblRNorm, txtfRNorm,
//                lblPutBackDanger, txtfPutBackDanger,
//                lblMoveDanger, txtfMoveDanger,
//                lblDistNorm, txtfDistNorm,
//                btnStart
//        );
//        grid.addColumn(0,vbox);
//
//        root.setCenter(grid);

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
