package hust.soict.globalict.aims.screen;

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import java.net.URL;
import java.util.ArrayList;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.disc.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AddItemScreenController implements Initializable {

    private Store store;
    private StoreScreen storeScreen;
    private AddItemScreen addItemScreen;

    public void setAddItemScreen(AddItemScreen addItemScreen) {
        this.addItemScreen = addItemScreen;
    }

    public void setStoreScreen(StoreScreen storeScreen) {
        this.storeScreen = storeScreen;
    }

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddCD;

    @FXML
    private Button btnAddDVD;

    @FXML
    private VBox extendBook;

    @FXML
    private VBox extendCD;

    @FXML
    private VBox extendDVD;

    @FXML
    private VBox root;

    @FXML
    private TextField tfBookAuthor;

    @FXML
    private TextField tfCategory;

    @FXML
    private TextField tfCost;

    @FXML
    private TextField tfDirector;

    @FXML
    private TextField tfDirectorCD;

    @FXML
    private TextField tfLength;

    @FXML
    private TextField tfTitle;

    @FXML
    private ToggleGroup typeSelection;

    @FXML
    void addBookSelected(ActionEvent event) {
        clearContent();
        extendBook.setVisible(true);
        extendBook.setDisable(false);
        extendCD.setVisible(false);
        extendCD.setDisable(true);
        extendDVD.setVisible(false);
        extendDVD.setDisable(true);
    }   

    @FXML
    void addCDSelected(ActionEvent event) {
        clearContent();
        extendCD.setVisible(true);
        extendCD.setDisable(false);
        extendBook.setVisible(false);
        extendBook.setDisable(true);
        extendDVD.setVisible(false);
        extendDVD.setDisable(true);
    }

    @FXML
    void addDVDSelected(ActionEvent event) {
        clearContent();
        extendDVD.setVisible(true);
        extendDVD.setDisable(false);
        extendCD.setVisible(false);
        extendCD.setDisable(true);
        extendBook.setVisible(false);
        extendBook.setDisable(true);
    }

    @FXML
    void btnAddBookPressed(ActionEvent event) {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        List<String> authors = new ArrayList<String>();
        for(String author : tfBookAuthor.getText().split(" ,"))
            authors.add(author);
        store.addItem(new Book(title, category, authors, cost));
        clearContent();
        resetStoreScreen();
    }

    @FXML
    void btnAddCDPressed(ActionEvent event) {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String director = tfDirectorCD.getText();
        store.addItem(new CompactDisc(title, category, cost, director));
        clearContent();
        resetStoreScreen();
    }

    @FXML
    void btnAddDVDPressed(ActionEvent event) {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String director = tfDirector.getText();
        int length = 0;
        if(!tfLength.getText().isEmpty())
            length = Integer.parseInt(tfLength.getText());
        store.addItem(new DigitalVideoDisc(title, category, director, length, cost));
        clearContent();
        resetStoreScreen();
    }


    public AddItemScreenController(Store store) {
        super();
        this.store = store;
    }

     @Override
    public void initialize(URL location, ResourceBundle resources) {
        activeConfirmButton(false);
        tfTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(tfTitle.getText().isEmpty())
                    activeConfirmButton(false);
                else    
                    activeConfirmButton(true);
            }
        });
        tfCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observe, String oldStr, String newStr) {
                try {
                    Float.parseFloat(tfCost.getText());
                    tfCost.setStyle("-fx-text-fill: black;");
                    if(!tfTitle.getText().isEmpty())
                        activeConfirmButton(true);
                    else    
                        activeConfirmButton(false);
                } catch(NumberFormatException e) {
                    tfCost.setStyle("-fx-text-fill: red;");
                    activeConfirmButton(false);
                }
            }
        });

        tfLength.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observe, String oldStr, String newStr) {
                if(!tfLength.getText().isEmpty()) {
                    try {
                        Integer.parseInt(tfLength.getText());
                        tfLength.setStyle("-fx-text-fill: black;");
                        if(isRequiedValid())
                            btnAddDVD.setDisable(false);
                        else
                            btnAddDVD.setDisable(true);
                    } catch(NumberFormatException e) {
                        tfLength.setStyle("-fx-text-fill: red;");
                        btnAddDVD.setDisable(true);
                    }
                } else if(isRequiedValid()) {
                    btnAddDVD.setDisable(false);
                    tfLength.setStyle("-fx-text-fill: black;");
                }
            }
        });
    }

    private void clearContent() {
        tfBookAuthor.setText("");
        tfCategory.setText("");
        tfCost.setText("");
        tfDirector.setText("");
        tfDirectorCD.setText("");
        tfLength.setText("");
        tfTitle.setText("");
    }

    private void activeConfirmButton(boolean value) {
        btnAddBook.setDisable(!value);
        btnAddCD.setDisable(!value);
        btnAddDVD.setDisable(!value);
    }

    private boolean isRequiedValid(){
        try {
            Float.parseFloat(tfCost.getText());
        } catch (NumberFormatException e){
            return false;
        }
        return !tfTitle.getText().isEmpty();
    }

    private void resetStoreScreen() {
        JOptionPane.showMessageDialog(null, "Item added to store!", "Notice", JOptionPane.INFORMATION_MESSAGE);
        storeScreen.setVisible(true);
        addItemScreen.setVisible(false);
        storeScreen.setMaxItemPage();
        if(storeScreen.getMaxItemPage() > storeScreen.getItemPage()) {
            storeScreen.getNextPageBtn().setEnabled(true);
        }
        storeScreen.getPage().setText("Page " + storeScreen.getItemPage() + "/" + storeScreen.getMaxItemPage());
        if(storeScreen.getItemPage() == storeScreen.getMaxItemPage())
            storeScreen.resetContentCenter();
    }
}
