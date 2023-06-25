package hust.soict.globalict.aims.screen;
import javax.swing.*;

import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.cart.Cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
private Media media;
public Media getMedia() {
    return media;
}
public MediaStore(Media media) {
	this.media = media ;
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	JLabel title = new JLabel(media.getTitle());
	title.setFont(new Font(title.getFont().getName(),Font.PLAIN,20));
	title.setAlignmentX(CENTER_ALIGNMENT);
	
	JLabel category = new JLabel(media.getCategory());
    title.setFont(new Font(category.getFont().getName(), Font.PLAIN, 18));
    category.setAlignmentX(CENTER_ALIGNMENT);
    
	JLabel cost = new JLabel (""+media.getCost()+ " $");
	cost.setAlignmentX(CENTER_ALIGNMENT);
	
	JPanel container = new JPanel();
	container.setLayout(new FlowLayout(FlowLayout.CENTER));
	
	JButton btnAddToCart = new JButton("Add to cart");
    btnAddToCart.addActionListener(new ActionListener() {
     private Cart cart;
    	public void actionPerformed(ActionEvent e) {
			cart.addItem(media);
            System.out.println("Item added");
        }
    });
    
    container.add(btnAddToCart);
    if (media instanceof Playable)
        container.add(new JButton("Play"));
this.add(Box.createVerticalGlue());
this.add(title);
this.add(category);
this.add(cost);
this.add(Box.createVerticalGlue());
this.add(container);

this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
}
}

