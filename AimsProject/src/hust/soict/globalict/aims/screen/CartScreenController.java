package hust.soict.globalict.aims.screen;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class CartScreenController implements Initializable {

    private Cart cart;

    private CartScreen cartScreen;

    public void setCartScreen(CartScreen cartScreen) {
        this.cartScreen = cartScreen;
    }
    private StoreScreen storeScreen;
    private PlaceOrderScreen placeOrderScreen;

    public void setStoreScreen(StoreScreen storeScreen) {
        this.storeScreen = storeScreen;
    }

    public void setPlaceOrderScreen(PlaceOrderScreen placeOrderScreen) {
        this.placeOrderScreen = placeOrderScreen;
    }

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TextField tfFilter;

    @FXML
    private Label totalPayText;

    @FXML
    private MenuItem menuAddBook;

    @FXML
    private MenuItem menuAddCD;

    @FXML
    private MenuItem menuAddDVD;

    @FXML
    private MenuItem menuViewCart;

    @FXML
    private MenuItem menuViewStore;

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        placeOrderScreen.setVisible(true);
        placeOrderScreen.getController().setTotalPayText();
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        cart.removeItem(tblMedia.getSelectionModel().getSelectedItem());
        setTotalPayText();
        JOptionPane.showMessageDialog(null, "Item removed", "Notice", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        tblMedia.getSelectionModel().getSelectedItem().play();
    }

    @FXML
    void viewStore(ActionEvent event) {
        cartScreen.setVisible(false);
        storeScreen.setVisible(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        tblMedia.setItems(cart.getItemsOrdered());  
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observableValue, Media oldValue, Media newValue) {
                if(newValue != null)
                    updateButtonBar(newValue);
            }    
        });

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observe, String oldStr, String newStr) {
                showFilteredMedia();
            }
        });
        
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if(media instanceof Playable) {
            btnPlay.setVisible(true);
        } else
            btnPlay.setVisible(false);
    }

    public void setTotalPayText() {
        totalPayText.setText(cart.totalCost() + " $");
    }

    void showFilteredMedia() {
        boolean filterByID = radioBtnFilterId.isSelected();
        ObservableList<Media> filterItems = FXCollections.observableArrayList(); 
        String filter  = tfFilter.getText().toLowerCase();
        if(!filterByID)
            for(Media media : cart.getItemsOrdered()) {
                if(media.getTitle().toLowerCase().contains(filter))
                    filterItems.add(media);
            }
        else
            for(Media media : cart.getItemsOrdered()) {
                if(String.valueOf(media.getId()).contains(filter))
                    filterItems.add(media);
            }
        tblMedia.setItems(filterItems);
    }
}