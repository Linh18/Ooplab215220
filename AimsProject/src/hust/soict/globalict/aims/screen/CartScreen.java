package hust.soict.globalict.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;

import hust.soict.globalict.aims.cart.Cart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * CartScreen
 */
public class CartScreen extends JFrame{
    private Cart cart;
    
    private CartScreenController controller;

    public CartScreenController getCartScreenController() {
        return controller;
    }

    public Cart getCart() {
        return cart;
    }

    public CartScreen(Cart cart) {
        super();

        this.cart = cart;
        this.controller = new CartScreenController(cart);
        controller.setCartScreen(this);

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}