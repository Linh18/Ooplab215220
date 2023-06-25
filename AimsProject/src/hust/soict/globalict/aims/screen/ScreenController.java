package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.store.Store;

public class ScreenController {
    private AddItemScreen addItemScreen;
    private StoreScreen storeScreen;
    private CartScreen cartScreen;
    private Store store;
    private Cart cart;
    private PlaceOrderScreen placeOrderScreen;

    
    public Store getStore() {
        return store;
    }


    public Cart getCart() {
        return cart;
    }


    public ScreenController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        placeOrderScreen = new PlaceOrderScreen(cart);
        placeOrderScreen.setVisible(false);

        storeScreen = new StoreScreen(store, cart);
        storeScreen.setVisible(true);

        cartScreen = new CartScreen(cart);
        cartScreen.setVisible(false);
        cartScreen.getCartScreenController().setStoreScreen(storeScreen);
        cartScreen.getCartScreenController().setPlaceOrderScreen(placeOrderScreen);

        
        addItemScreen = new AddItemScreen(store);
        addItemScreen.setVisible(false);
        addItemScreen.getController().setStoreScreen(storeScreen);

        storeScreen.setAddItemScreen(addItemScreen);
        storeScreen.setCartScreen(cartScreen);
    }
}