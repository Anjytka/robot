package npp.robot.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import npp.robot.core.BaseController;
import npp.robot.core.Place;
import npp.robot.services.GenerationService;
import npp.robot.windows.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputDataController extends BaseController
{
    public static String URL_FXML = "/fxml/input_data.fxml";

//
//    @FXML private TextField firstNameField;
//    @FXML private TextField lastNameField;
//    @FXML private Label messageLabel;
    @FXML private TextField xCount;
    @FXML private TextField yCount;

    private Place place = Place.getInstance();

    public void startGenerateData() {
        int xCellCount = Integer.parseInt(xCount.getText());
        int yCellCount = Integer.parseInt(yCount.getText());
        log.debug("Count of cells: {} X {}", xCellCount, yCellCount);
        GenerationService gService = new GenerationService();
        gService.dataGenerator(xCellCount, yCellCount);
        MainWindow.getNavigation().load(PlaceController.URL_FXML, 800, 650).show();
//        String firstName = firstNameField.getText();
//        String lastName = lastNameField.getText();
//
//        StringBuilder builder = new StringBuilder();
//
//        if (!StringUtils.isEmpty(firstName)) {
//            builder.append(firstName);
//        }
//
//        if (!StringUtils.isEmpty(lastName)) {
//            if (builder.length() > 0) {
//                builder.append(" ");
//            }
//            builder.append(lastName);
//        }
//
//        if (builder.length() > 0) {
//            String name = builder.toString();
//            log.debug("Saying hello to {}", name);
//            messageLabel.setText("Hello " + name);
//        } else {
//            log.debug("Neither first name nor last name was set, saying hello to anonymous person");
//            messageLabel.setText("Hello mysterious person");
//        }
    }
}
