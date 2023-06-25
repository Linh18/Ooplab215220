package hust.soict.globalict.aims.media;
import java.util.Comparator;

public class SortByCostThenTitle implements Comparator<Media> {
    @Override
    public int compare(Media o1, Media o2) {
        if(o1.getCost() != o2.getCost())
            return o1.getTitle().compareTo(o2.getTitle());
        else    
            return o1.getCost() > o2.getCost() ? 1 : 0;
    }
}
