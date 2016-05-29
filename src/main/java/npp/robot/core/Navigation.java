package npp.robot.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 28.05.16.
 */
public class Navigation {

    private final Stage stage;
    private final Scene scene;
    private Object parameterFromView;

    private List<IController> controllers = new ArrayList<>();


    public Navigation(Stage stage)
    {
        this.stage = stage;
        scene = new Scene(new Pane());
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
    }

    public Navigation(Stage stage, int w, int h)
    {
        this.stage = stage;
        scene = new Scene(new Pane(), w, h);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
    }

    public IController load(String sUrl)
    {
        try {

            //loads the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            Node root = fxmlLoader.load();

            IController controller = fxmlLoader.getController();
            controller.setView(root);

            return controller;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Show(IController controller)
    {
        try {
            scene.setRoot((Parent) controller.getView());
            controllers.add(controller);

            System.out.println("Add to history: " + controller.toString() + ". Total scenes: " + controllers.size());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void GoBack()
    {
        if (controllers.size() > 1)
        {
            controllers.remove(controllers.get(controllers.size() - 1));
            scene.setRoot((Parent) controllers.get(controllers.size() - 1).getView());
        }

        System.out.println("GoBack: " + controllers.get(controllers.size() - 1).toString() + ". Total scenes: " + controllers.size());
    }


    public void ClearHistory()
    {
        while (controllers.size() > 1)
            controllers.remove(0);

        System.out.println("ClearHistory. Total scenes: " + controllers.size());
    }
}