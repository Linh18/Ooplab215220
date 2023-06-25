package hust.soict.globalict.aims.disc;
import hust.soict.globalict.aims.media.Disc;
import hust.soict.globalict.aims.media.Playable;
import javax.swing.JOptionPane;

public class DigitalVideoDisc extends Disc implements Playable {
    
    private static int nbDigitalVideoDisc = 0;

    //Getters
    public static int getNbDVD() {
        return nbDigitalVideoDisc;
    }
    public String getDirector(){
        return director;
    }
    public int getLength(){
        return length;
    }
    
    //Setters
    public void setDirector(String director){
        this.director = director;
    }
    public void setLength(int length){
        this.length = length;
    }
    //Build Contructors

    public DigitalVideoDisc(String title){
        setTitle(title);
        nbDigitalVideoDisc++;
        incItem();
        setId();
    }

    public DigitalVideoDisc(String title, String category, float cost){
        setCategory(category);
        setTitle(title);
        setCost(cost);
        nbDigitalVideoDisc++;
        incItem();
        setId();
    }

    public DigitalVideoDisc(String title, String category, String director, float cost){
        setDirector(director);
        setCategory(category);
        setTitle(title);
        setCost(cost);
        nbDigitalVideoDisc++;
        incItem();
        setId();
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost){
        setTitle(title);
        setCategory(category);
        setDirector(director);
        setLength(length);
        setCost(cost);
        nbDigitalVideoDisc++;
        incItem();
        setId();
    }

    public String toString()
    {
        String discInfo = getId() + ". DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + "$"; 
        return discInfo;
    }

    @Override
    public void play() {
        if(length > 0)
            JOptionPane.showMessageDialog(null, "Playing DVD...");
        else
            JOptionPane.showMessageDialog(null, "DVD's content is empty");
    }
}