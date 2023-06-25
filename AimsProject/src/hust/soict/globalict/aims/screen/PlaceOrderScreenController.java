package hust.soict.globalict.aims.screen;

import javax.swing.JOptionPane;

import hust.soict.globalict.aims.cart.Cart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlaceOrderScreenController {
   

   @FXML
   private Button btnCancel;

   @FXML
   private Button btnConfirm;

   @FXML
   private VBox root;

   @FXML
   private TextField tfBank;

   @FXML
   private TextField tfBankAccount;

   @FXML
   private TextField tfCustomer;

   @FXML
   private TextField tfAddress;

   @FXML
   private TextField tfPhone;

   @FXML
   private Label tfTotalPay;

   @FXML
   void btnPurchasePressed(ActionEvent event) {
      String info = "Customer: " + tfCustomer.getText() + "\n"
                  + "Phone: " + tfPhone.getText() + "\n" 
                  + "Address: " + tfAddress.getText() + "\n"
                  + tfBank.getText() + ": " + tfBankAccount.getText();
      int confirm = JOptionPane.showConfirmDialog(null, info, "Confirm payment", JOptionPane.YES_NO_OPTION);
      if(confirm == JOptionPane.YES_OPTION) {
         JOptionPane.showMessageDialog(null, "Payment succesful!\nThank you!");
         while(!cart.getItemsOrdered().isEmpty())
            cart.removeItem(cart.getItemsOrdered().get(0));
         placeOrderScreen.setVisible(false);
      }
   }

   @FXML
   void tbnCancelPressed(ActionEvent event) {
      placeOrderScreen.setVisible(false);
   }

   private Cart cart;
   private PlaceOrderScreen placeOrderScreen;


   public void setPlaceOrderScreen(PlaceOrderScreen placeOrderScreen) {
      this.placeOrderScreen = placeOrderScreen;
   }

   public PlaceOrderScreenController(Cart cart) {
      this.cart = cart;
   }

   public void setTotalPayText() {
      tfTotalPay.setText("Total pay: " + cart.totalCost() + " $");
   }
}