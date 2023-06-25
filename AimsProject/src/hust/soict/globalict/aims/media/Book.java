package hust.soict.globalict.aims.media;
import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private static int nbBook = 0;

    private List<String> authors = new ArrayList<String>(); 
    public int getNbBook() {
        return nbBook;
    }
    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String authorName) {
        if(!authors.contains(authorName))
            authors.add(authorName);
    }

    public void addAuthors(List<String> authors) {
        for (String author : authors)
            addAuthor(author);
    }

    public void removeAuthor(String authorName) {
        if(authors.contains(authorName)) {
            authors.remove(authorName);
        }
    }

    //Contructors
    public Book(String title){
        setTitle(title);
        nbBook++;
        incItem();
        setId();
        nbBook++;
    }

    public Book(String title, String category, float cost){
        setCategory(category);
        setTitle(title);
        setCost(cost);
        nbBook++;
        incItem();
        setId();
        nbBook++;
    }

    public Book(String title, String category, List<String> authors, float cost){
        addAuthors(authors);
        setTitle(title);
        setCost(cost);
        nbBook++;
        incItem();
        setId();
        nbBook++;
    }

    public Book(String title, String category, String author, float cost){
        setTitle(title);
        setCategory(category);
        addAuthor(author);
        setCost(cost);
        nbBook++;
        incItem();
        setId();
        nbBook++;
    }

    public String toString()
    {
        String bookInfo = getId() + ". Book - " + getTitle() + " - " + getCategory() + " - " + getAuthors() + ": " + getCost() + "$"; 
        return bookInfo;
    }
}