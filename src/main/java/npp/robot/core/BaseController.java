package npp.robot.core;

import javafx.scene.Node;
import npp.robot.windows.MainWindow;

/**
 * Created by mac on 28.05.16.
 */
public class BaseController  implements IController {

    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView (Node view){
        this.view = view;
    }

    @Override
    public void show() {
        preShowing();
        MainWindow.getNavigation().Show(this);
        postShowing();
    }

    public void preShowing()
    {
    }

    public void postShowing()
    {
    }
}