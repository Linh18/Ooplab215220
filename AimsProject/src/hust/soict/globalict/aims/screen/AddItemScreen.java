package hust.soict.globalict.aims.screen;

import javax.swing.JFrame;
import java.io.IOException;

import hust.soict.globalict.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddItemScreen extends JFrame {

    private Store store;

    public Store getStore() {
        return store;
    }

    private AddItemScreenController controller;

    public AddItemScreenController getController() {
        return controller;
    }

    public AddItemScreen(Store store) {
        super();

        this.store = store;
        this.controller = new AddItemScreenController(store);
        this.controller.setAddItemScreen(this);
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Add Item");
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItemScreen.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setSize(480, 640);  
    }
}