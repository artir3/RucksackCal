package source;

import java.util.ArrayList;

public class Bag {
	private String bagName;
	private int bagWeight;
	private ArrayList<Items> bagArrayList;

	public Bag(String bagName) {
		super();
		this.bagName = bagName;
		this.bagWeight = 0;
		this.bagArrayList = new ArrayList<Items>();
	}

	public Bag(String bagName, int bagWeight, ArrayList<Items> bagArrayList) {
		super();
		this.bagName = bagName;
		this.bagWeight = bagWeight;
		this.bagArrayList = bagArrayList;
	}

	public void deleteBag() {
		bagArrayList.clear();
		setBagName("");
		setBagWeight(0);
		bagArrayList = null;
	}

	public void addItem(String name, int weight, int amount) {
		addItem(name, weight, amount, " ");
	}

	public void addItem(String name, int weight, int amount, String note) {
		bagArrayList.add(new Items(name, weight, amount, note));
		setBagWeight(getBagWeight() + (weight * amount));
	}

	public void removeItem(String name) {
		int temp = indexOfItem(name);
		if (temp > -1) {
			setBagWeight(getBagWeight() - bagArrayList.get(temp).totalWeight());
			bagArrayList.remove(temp);
		}
	}

	public void changeName(String name, String newName) {
		bagArrayList.get(indexOfItem(name)).setName(newName);
	}

	public void changeNote(String name, String note) {
		bagArrayList.get(indexOfItem(name)).setNote(note);
	}

	public void changeWeight(String name, int weight) {
		if (weight > -1)
			bagArrayList.get(indexOfItem(name)).setWeight(weight);
		recalculateBagWeight();
	}

	public void changeAmount(String name, int amount) {
		if (amount > -1)
			bagArrayList.get(indexOfItem(name)).setAmount(amount);
		recalculateBagWeight();
	}

	public void printBag() {
		if (bagArrayList.isEmpty()!=true) {
			System.out.println(bagName + ", Weight=" + bagWeight);
			System.out.printf("-> %1$-15s | %2$-8s | %3$-6s | %4$-8s |  %5$-100s\n", "name", "mass [g]", "amount",
					"Sum [g]", "note");
			for (Items items : bagArrayList) {
				System.out.printf("-> %1$-15s | %2$-8d | %3$-6d | %4$-8d | %5$-100s\n", items.getName(),
						items.getWeight(), items.getAmount(), items.totalWeight(), items.getNote());
			}
		} else
			System.out.println("No find any bag");

	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public int getBagWeight() {
		return bagWeight;
	}

	private void setBagWeight(int bagWeight) {
		this.bagWeight = bagWeight;
	}

	public void recalculateBagWeight() {
		int temp = 0;
		for (Items items : bagArrayList) {
			temp += items.totalWeight();
		}
		this.bagWeight = temp;
	}

	private int indexOfItem(String name) {
		int temp = -1;
		for (Items items : bagArrayList) {
			if (items.getName() == name)
				temp = bagArrayList.indexOf(items);
		}
		return temp;
	}
}
