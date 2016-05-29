package npp.robot.core;

import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * Created by mac on 28.05.16.
 */
public interface IController {
    Node getView();
    void setView(Node view);

//    Scene getScene();
//    void setScene(Scene scene);

    void show();
}
