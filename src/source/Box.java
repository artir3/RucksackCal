package source;

import java.util.ArrayList;
/**
 * Created by Artur Artir Markowski on 2017-01-02.
 */
class Box {
	private String boxName = " ";
	private int boxWeight = 0;
	private ArrayList<Item> box = null;

	Box(String boxName) {
		super();
		this.boxName = boxName;
		this.box = new ArrayList<>();
	}

	Box(String boxName, int boxWeight, ArrayList<Item> box) {
		super();
		this.boxName = boxName;
		this.boxWeight = boxWeight;
		this.box = box;
	}

	void clearBag() {
		if (isExist()) {
			box.clear();
			setBoxWeight(0);
		}
	}

	void deleteBag() {
		if (isExist()) {
			clearBag();
			setBoxName("");
			box = null;
		}
	}

	boolean isExist(){
		return box != null;
	}

	void addBoxItem(String name, int weight, int amount) {
		addBoxItem(name, weight, amount, " ");
		setBoxWeight(getBoxWeight() + (weight * amount));
	}

	void addBoxItem(String name, int weight, int amount, String note) {
		box.add(new Item(name, weight, amount, note));
		setBoxWeight(getBoxWeight() + (weight * amount));
	}

	void removeBoxItem(String name) {
		if (isExist()){
			int temp = indexOfItem(name);
			if (temp > -1) {
				setBoxWeight(getBoxWeight() - box.get(temp).totalWeight());
				box.remove(temp);
			}
		}
	}

	void changeName(String name, String newName) {
		if (isExist()) box.get(indexOfItem(name)).setName(newName);
	}

	void changeItemNote(String name, String note) {
		if (isExist()) box.get(indexOfItem(name)).setNote(note);
	}

	void changeItemWeight(String name, int weight) {
		if (weight > -1)
			box.get(indexOfItem(name)).setWeight(weight);
		recalculateBagWeight();
	}

	void changeItemAmount(String name, int amount) {
		if (isExist()) {
			if (amount > -1)
				box.get(indexOfItem(name)).setAmount(amount);
			recalculateBagWeight();
		}
	}

	void printBag() {
		if (isExist()) {
			System.out.println(boxName + ", Weight=" + boxWeight);
			System.out.printf("-> %1$-15s | %2$-8s | %3$-6s | %4$-8s |  %5$-100s\n", "name", "mass [g]", "amount",
					"Sum [g]", "note");
			for (Item item : box) {
				System.out.printf("-> %1$-15s | %2$-8d | %3$-6d | %4$-8d | %5$-100s\n", item.getName(),
						item.getWeight(), item.getAmount(), item.totalWeight(), item.getNote());
			}
		} else {
			System.out.println("No find that bag");
		}

	}

	private String getBoxName() {
		return boxName;
	}

	private void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	private int getBoxWeight() {
		return boxWeight;
	}

	private void setBoxWeight(int boxWeight) {
		this.boxWeight = boxWeight;
	}

	private void recalculateBagWeight() {
		int temp = 0;
		for (Item item : box) {
			temp += item.totalWeight();
		}
		this.boxWeight = temp;
	}

	private int indexOfItem(String name) {
		int temp = -1;
		for (Item item : box) {
			if (name.equals(item.getName()))
				temp = box.indexOf(item);
		}
		return temp;
	}
}
