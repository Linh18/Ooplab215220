package hust.soict.globalict.aims.media;
public class Disc extends Media{
    private static int nbDisc = 0;
    protected int length;
    protected String director;
    
    public static int getNbCD() {
        return nbDisc;
    }
    public int getLength() {
        return length;
    }
    public String getDirector() {
        return director;
    }

    //Setters
    public void setLength(int length) {
        this.length = length;
    }
    public void setDirector(String director) {
        this.director = director;
    }
}