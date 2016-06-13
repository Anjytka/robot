package npp.robot.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import npp.robot.services.GenerationService;
import npp.robot.windows.MainWindow;

public class InputDataController extends BaseController
{
    public static String URL_FXML = "/fxml/input_data.fxml";

    @FXML private TextField xCount;
    @FXML private TextField yCount;
    @FXML private TextField suffFill;
    @FXML private TextField normRadDiff;
    @FXML private TextField dangerBack;
    @FXML private TextField dangerMove;
    @FXML private TextField permitDist;
    @FXML private CheckBox  maxFilling;


    public void startGenerateData() {
        int xCellCount = 0;
        int yCellCount = 0;
        try {
//            xCellCount = Integer.parseInt(xCount.getText());
//            yCellCount = Integer.parseInt(yCount.getText());
//            place.setxMaxCellCount(xCellCount);
//            place.setyMaxCellCount(yCellCount);
//            place.setSuffFill(Double.parseDouble(suffFill.getText()));
//            place.setNormRadDiff(Double.parseDouble(normRadDiff.getText()));
//            place.setDangerBack(Double.parseDouble(dangerBack.getText()));
//            place.setDangerMove(Double.parseDouble(dangerMove.getText()));
//            place.setPermitDist(Integer.parseInt(permitDist.getText()));

            xCellCount = Integer.parseInt("10");
            yCellCount = Integer.parseInt("10");
            place.setxMaxCellCount(xCellCount);
            place.setyMaxCellCount(yCellCount);
            place.setSuffFill(Double.parseDouble("0.8"));    //0.0 .. 1.0
            place.setNormRadDiff(Double.parseDouble("0.2")); //0.0 .. 1.0
            place.setDangerBack(Double.parseDouble("0.1"));  //0.0 .. 1.0
            place.setDangerMove(Double.parseDouble("0.1"));  //0.0 .. 1.0
            place.setPermitDist(Integer.parseInt("2"));      //1...

            place.setMaxFilling(maxFilling.isSelected());

            GenerationService gService = new GenerationService();
            gService.dataGenerator(xCellCount, yCellCount);
            MainWindow.getNavigation().load(PlaceController.URL_FXML, 800, 650).show();
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
        }
    }
}
