package npp.robot.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import npp.robot.controllers.IController;

import java.util.ArrayList;
import java.util.List;

public class Navigation {

    private final Stage stage;
    private final Scene scene;
    private Object parameterFromView;

    private List<IController> controllers = new ArrayList<>();


    public Navigation(Stage stage) {
        this.stage = stage;
        scene = new Scene(new Pane());
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
    }

    public Navigation(Stage stage, int w, int h) {
        this.stage = stage;
        scene = new Scene(new Pane(), w, h);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
    }

    public IController load(String sUrl) {
        return this.load(sUrl, this.scene.getWidth(), this.scene.getHeight());
    }

    public IController load(String sUrl, double w, double h) {
        try {

            //loads the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            this.stage.setWidth(w);
            this.stage.setHeight(h);

            Node root = fxmlLoader.load();

            IController controller = fxmlLoader.getController();
            controller.setView(root);

            return controller;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Show(IController controller) {
        try {
            scene.setRoot((Parent) controller.getView());
            controllers.add(controller);

            System.out.println("Add to history: " + controller.toString() + ". Total scenes: " + controllers.size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return this.stage;
    }

    public void goBack() {
        this.goBack(this.scene.getWidth(), this.scene.getHeight());
    }

    public void goBack(double w, double h) {
        if (controllers.size() > 1) {
            this.stage.setWidth(w);
            this.stage.setHeight(h);
            controllers.remove(controllers.get(controllers.size() - 1));
            scene.setRoot((Parent) controllers.get(controllers.size() - 1).getView());
        }
    }


    public void ClearHistory() {
        while (controllers.size() > 1)
            controllers.remove(0);
    }
}