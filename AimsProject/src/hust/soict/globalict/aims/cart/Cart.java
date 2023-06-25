package hust.soict.globalict.aims.cart;

import javax.swing.JOptionPane;

import hust.soict.globalict.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    private int qtyOrdered = 0;

    public void addItem(Media item){
        if(qtyOrdered >= MAX_NUMBER_ORDERED){
            JOptionPane.showMessageDialog(null, "The cart is almost full", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else if (itemsOrdered.contains(item)) {
            JOptionPane.showMessageDialog(null, "Item is already exist in cart", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else{
            itemsOrdered.add(item);
            JOptionPane.showMessageDialog(null, "Item added to cart", "Notice", JOptionPane.INFORMATION_MESSAGE); 
        }
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    //Add a array of discs into cart
    public void addItems(Media [] items){
        for (Media item : items)
            if(item != null)
                addItem(item);
    } 

    //remove all discs be the same as parameter disc
    public void removeItem(Media disc){
        boolean flag = false;
        if(itemsOrdered.contains(disc)) {
            qtyOrdered--;
            itemsOrdered.remove(disc);
            flag = true;
        }
        if (!flag)
            JOptionPane.showMessageDialog(null, "Not found item!", "Notice", JOptionPane.INFORMATION_MESSAGE);
    }

    //Return sum of dvd's cost existing in cart
    public float totalCost(){
        float sumCost = 0;
        for (Media item : itemsOrdered){
            sumCost += item.getCost();
        }
        return sumCost;
    }
    

    //Show all item existing in cart
    public void showItemsOrdered() {
        System.out.println("Order Items:");
        
        for (Media item : itemsOrdered){
            System.out.println(item.toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
    }

    //Search item by id
    public void searchItem(int id) {
        boolean found = false;
        for (Media item : itemsOrdered)
        {
            if (item.isMatch(id)){
                System.out.println(item.toString());
                found = true;
            }
        } 
        if (!found)
            System.out.println("No item match!");
    }

    //Search item by title
    public void searchItem(String title) {
        boolean found = false;
        for (Media item : itemsOrdered) {
            if (item.isMatch(title)){
                System.out.println(item.toString());
                found = true;
            }
        } 
        if (!found)
            System.out.println("No item match!");
    }


}