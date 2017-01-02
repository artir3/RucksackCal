package source;
/**
 * Created by Artur Artir Markowski on 2017-01-02.
 */
class Item {
	private String name = " ";
	private int weight = 0;
	private int amount = 0;
	private String note = " ";

	Item(String name, int weight, int amount) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
	}

	Item(String name, int weight, int amount, String note) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
		this.note = note;
	}

	int totalWeight() {
		return weight * amount;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	int getWeight() {
		return weight;
	}

	void setWeight(int weight) {
		this.weight = weight;
	}

	int getAmount() {
		return amount;
	}

	void setAmount(int amount) {
		this.amount = amount;
	}

	String getNote() {
		return note;
	}

	void setNote(String note) {
		this.note = note;
	}
}
