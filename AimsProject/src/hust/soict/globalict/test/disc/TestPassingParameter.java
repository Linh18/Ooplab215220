package hust.soict.globalict.test.disc;

import hust.soict.globalict.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			DigitalVideoDisc jungleDVD = new DigitalVideoDisc ("Jungle");
			DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc ("Cinderella");
			
			swap(jungleDVD, cinderellaDVD);
			System.out.println("jungle dd title: " + jungleDVD.getTitle());
			System.out.println("Cinderella dvd title: " + cinderellaDVD.getTitle());
			changeTitle(jungleDVD,cinderellaDVD.getTitle());
			System.out.println("jungle dvd title:"+jungleDVD.getTitle());
	}
	public static void swap (Object o1, Object o2)
	{
		Object tmp = o1;
		o1=02;
		o2=tmp;
	}
	public static void changeTitle(DigitalVideoDisc dvd, String title) {
		String oldTitle = dvd.getTitle();
		dvd.setTitle(title);
		dvd= new DigitalVideoDisc (oldTitle);
		}
	public static void rewrite_swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		String tmpTitle=dvd1.getTitle();
		String tmpCategory = dvd1.getCategory();
		String tmpDirector = dvd1.getDirector();
	    int tmpLength = dvd1.getLength();
	    float tmpCost = dvd1.getCost();
	    
	    dvd1.setTitle(dvd2.getTitle());
	    dvd1.setCategory(dvd2.getCategory());
	    dvd1.setCost(dvd2.getCost());
	    dvd1.setDirector(dvd2.getDirector());
	    dvd1.setLength(dvd2.getLength());
	    
	    dvd2.setTitle(tmpTitle);
	    dvd2.setCategory(tmpCategory);
	    dvd2.setCost(tmpCost);
	    dvd2.setDirector(tmpDirector);
	    dvd2.setLength(tmpLength);
	    }
	}
