package hust.soict.globalict.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterControler {
    private Color customColor;
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton eraseSelectBtn;

    @FXML
    private BorderPane root;

    @FXML
    private RadioButton selectPenBtn;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawing(ActionEvent event) {
        customColor = Color.BLACK;
        eraseSelectBtn.setSelected(false);
        selectPenBtn.setSelected(true);
    }

    @FXML
    void erasing(ActionEvent event) {
        customColor = Color.WHITE;
        eraseSelectBtn.setSelected(true);
        selectPenBtn.setSelected(false);
    }

    @FXML
    void drawingAreaMouseDraged(MouseEvent event) {
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, customColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}