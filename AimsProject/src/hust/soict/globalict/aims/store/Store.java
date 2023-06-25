package hust.soict.globalict.aims.store;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import hust.soict.globalict.aims.media.Media;

public class Store {
    private static int MAX_NUMBER_ITEMS_IN_STORE = 1000000;
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();     
    private int numberOfItemsInStore = 0;
    
    public Media findByTitle(String title) {
        for(Media item : itemsInStore)
            if (item.getTitle().equals(title))
                return item;
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
    public int getNumberOfItemsInStore() {
        return numberOfItemsInStore;
    }
    
    //Add a new dvd into store
    public void addItem(Media item)
    {
        if(numberOfItemsInStore == MAX_NUMBER_ITEMS_IN_STORE){
            JOptionPane.showMessageDialog(null, "Store is full", "Warning", JOptionPane.INFORMATION_MESSAGE);
        
        } else if (itemsInStore.contains(item)) {
            JOptionPane.showMessageDialog(null, "This item is existed in store", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            itemsInStore.add(item);
            numberOfItemsInStore++;
        }
    }

    //Remove a disc from store (remove object has the same address to dvd not the same properties to dvd)
    public void removeItem(Media item){
        boolean changed = false;
        if(itemsInStore.contains(item)) {
            itemsInStore.remove(item);
            numberOfItemsInStore--;
            changed = true;
        }
        if (!changed)
            JOptionPane.showMessageDialog(null, "No item was remove!", "Notice", JOptionPane.INFORMATION_MESSAGE);        
    }
}