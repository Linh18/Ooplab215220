package hust.soict.globalict.aims.media;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CompactDisc extends Disc implements Playable{
    private static int nbCD = 0;
    
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();
    
    //Getters
    public static int getNbCD() {
        return nbCD;
    }
    public String getArtist() {
        return artist;
    }
    public int getLength() {
        for(Track track : tracks) {
            length += track.getLength() ;
        }
        return length;
    }

    //Contructors
    public CompactDisc(String title, String category, float cost, String artist, List<Track> tracks) {
        setTitle(title);
        setCategory(category);
        setCost(cost);
        this.artist = artist;
        this.tracks = tracks;
        incItem();
        setId();
    }

    public CompactDisc(String title, String category, float cost, String artist) {
        setTitle(title);
        setCategory(category);
        setCost(cost);
        this.artist = artist;
        incItem();
        setId();
    }

    public CompactDisc(String title, String category, float cost) {
        setTitle(title);
        setCategory(category);
        setCost(cost);
        incItem();
        setId();
    }

    //Add and Remove Tracks
    public void addTrack(Track track) {
        if (!tracks.contains(track))
            tracks.add(track);
    }

    public void removeTrack(Track track) {
        if(tracks.contains(track))
            tracks.remove(track);
    }

    public String toString() {
        String discInfo = getId() + ". CD - " + getTitle() + " - " + getCategory() + " - " + getArtist() +" - " + getLength() + " : " + getCost() + "$"; 
        return discInfo;
    }
    
    @Override
    public void play() {
        if(!tracks.isEmpty()) {
            for(Track track : tracks)
                track.play();
        } else {
            JOptionPane.showMessageDialog(null, "CD's content is empty");
        }
    }
}