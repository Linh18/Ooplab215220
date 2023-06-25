package hust.soict.globalict.test.disc;

import hust.soict.globalict.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {

    public static void swapDVD(Object a, Object b){
        Object tmp = a;
        a = b;
        b = tmp;
    }
    public static void swapDVD(DigitalVideoDisc a, DigitalVideoDisc b){
        //swap all properties of a and b, not swap a and b
        //Example with title
        String tmp = a.getTitle();
        a.setTitle(b.getTitle());
        b.setTitle(tmp);
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title){
        String oldTilte = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTilte);
    }

    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        swapDVD(jungleDVD, cinderellaDVD);

        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: "+ cinderellaDVD.getTitle());

        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
    }
    
}