package source;

public class Main {

	public static void main(String[] args) {



	}


	private void elementTest(){
		Element element = new Element.Builder("nazwa").amount(10).weight(100).build();
		System.out.println(element);
		System.out.println(element.getAmount());
		System.out.println(element.getName());
		System.out.println(element.getWeight());
		System.out.println(element.getTotalWeight());
	}

	void boxTest(){
		Box box = new Box.Builder("plecak").build();
		box.addElement("plecak", 1850, 1);
		box.addElement("spiwor", 950, 1);
		box.addElement("karimata", 300, 1);
		box.printBag();
		System.out.println("Change amount of: spiwor to 2");
		box.changeElementAmount("spiwor", 2);
		box.printBag();
		System.out.println("Change weight of: plecak to 1200");
		box.changeElementWeight("plecak", 1200);
		box.printBag();
		System.out.println("Remove element: spiwor");
		box.removeBoxElement("spiwor");
		box.printBag();
		System.out.println("Clear the bag");
		box.clearBag();
		box.printBag();
	}

}
