package source;
/**
 * Created by Artur Artir Markowski on 2017-01-02.
 */
import java.util.ArrayList;

public class Box {
	private String boxName = " ";
	private int boxWeight = 0;
	private ArrayList<Item> box = null;

	public Box(String boxName) {
		super();
		this.boxName = boxName;
		this.box = new ArrayList<>();
	}

	public Box(String boxName, int boxWeight, ArrayList<Item> box) {
		super();
		this.boxName = boxName;
		this.boxWeight = boxWeight;
		this.box = box;
	}

	public void clearBag() {
		box.clear();
		setBoxWeight(0);
	}

	public void deleteBag() {
		clearBag();
		setBoxName("");
		box = null;
	}

	public boolean isExist(){
		return box != null;
	}

	public void addBoxItem(String name, int weight, int amount) {
		addBoxItem(name, weight, amount, " ");
	}

	public void addBoxItem(String name, int weight, int amount, String note) {
		box.add(new Item(name, weight, amount, note));
		setBoxWeight(getBoxWeight() + (weight * amount));
	}

	public void removeBoxItem(String name) {
		if (isExist()){
			int temp = indexOfItem(name);
			if (temp > -1) {
				setBoxWeight(getBoxWeight() - box.get(temp).totalWeight());
				box.remove(temp);
			}
		}
	}

	public void changeName(String name, String newName) {
		if (isExist()) box.get(indexOfItem(name)).setName(newName);
	}

	public void changeItemNote(String name, String note) {
		if (isExist()) box.get(indexOfItem(name)).setNote(note);
	}

	public void changeItemWeight(String name, int weight) {
		if (weight > -1)
			box.get(indexOfItem(name)).setWeight(weight);
		recalculateBagWeight();
	}

	public void changeItemAmount(String name, int amount) {
		if (amount > -1)
			box.get(indexOfItem(name)).setAmount(amount);
		recalculateBagWeight();
	}

	public void printBag() {
		if (isExist()) {
			System.out.println(boxName + ", Weight=" + boxWeight);
			System.out.printf("-> %1$-15s | %2$-8s | %3$-6s | %4$-8s |  %5$-100s\n", "name", "mass [g]", "amount",
					"Sum [g]", "note");
			for (Item item : box) {
				System.out.printf("-> %1$-15s | %2$-8d | %3$-6d | %4$-8d | %5$-100s\n", item.getName(),
						item.getWeight(), item.getAmount(), item.totalWeight(), item.getNote());
			}
		} else {
			System.out.println("No find any bag");
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
			if (item.getName() == name)
				temp = box.indexOf(item);
		}
		return temp;
	}
}
