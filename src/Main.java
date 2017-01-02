import source.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box box = new Box("plecak");
		box.addBoxItem("plecak", 1850, 1, " ");
		box.addBoxItem("śpiwor", 950, 1, " ");
		box.addBoxItem("karimata", 300, 1, " ");
		box.addBoxItem("hamak", 460, 1, "");
		box.addBoxItem("woda", 1000, 2, "dwie rozne butelki");
		box.addBoxItem("krakersy", 150, 2, "");
		box.addBoxItem("garnek", 100, 1, " ");
		box.printBag();
				
		box.removeBoxItem("krakersy");
		box.changeItemAmount("woda", 3);
		box.changeItemWeight("plecak", 1300);
		box.changeItemNote("plecak", "Plecak zamieniony z 40l na 25l");
		box.changeName("plecak", "plecaczek");
		box.printBag();
		box.clearBag();


		box.printBag();
		
	}

}
