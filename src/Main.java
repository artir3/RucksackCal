import source.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bag bag = new Bag("plecak");
		bag.addItem("plecak", 1850, 1, " ");
		bag.addItem("śpiwor", 950, 1, " ");
		bag.addItem("karimata", 300, 1, " ");
		bag.addItem("hamak", 460, 1, "");
		bag.addItem("woda", 1000, 2, "dwie rozne butelki");
		bag.addItem("krakersy", 150, 2, "");
		bag.addItem("garnek", 100, 1, " ");
		bag.printBag();
				
		bag.removeItem("krakersy");
		bag.changeAmount("woda", 3);
		bag.changeWeight("plecak", 1300);
		bag.changeNote("plecak", "Plecak zamieniony z 40l na 25l");
		bag.changeName("plecak", "plecaczek");
		bag.printBag();
		
		bag.deleteBag();
		bag.printBag();
		
	}

}
