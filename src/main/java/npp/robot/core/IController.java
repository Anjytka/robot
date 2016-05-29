package npp.robot.core;

import javafx.scene.Node;

/**
 * Created by mac on 28.05.16.
 */
public interface IController {
    Node getView();
    void setView(Node view);

    void show();
}
