package npp.robot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateDataController
{
    private static final Logger log = LoggerFactory.getLogger(GenerateDataController.class);
//
//    @FXML private TextField firstNameField;
//    @FXML private TextField lastNameField;
//    @FXML private Label messageLabel;
    @FXML
    private TextField uCount;



    public void dataHandler() {

        int unitCount = Integer.parseInt(uCount.getText());


        log.debug("It's work");
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
