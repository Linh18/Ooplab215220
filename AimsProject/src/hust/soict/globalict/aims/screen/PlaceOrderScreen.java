package hust.soict.globalict.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;

import hust.soict.globalict.aims.cart.Cart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PlaceOrderScreen extends JFrame{
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    private PlaceOrderScreenController controller;

    public PlaceOrderScreenController getController() {
        return controller;
    }

    public PlaceOrderScreen(Cart cart) {
        this.cart = cart;
        controller = new PlaceOrderScreenController(cart);
        controller.setPlaceOrderScreen(this);
        JFXPanel frame = new JFXPanel();
        this.add(frame);
        setTitle("Place Order");
        setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PlaceOrderScene.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    frame.setScene(new Scene(root));    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        setSize(480, 640);
    }
}