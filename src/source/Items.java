package source;

public class Items {
	private String name = " ";
	private int weight = 0;
	private int amount = 0;
	private String note = " ";

	public Items(String name, int weight, int amount) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
		this.note = " ";
	}

	public Items(String name, int weight, int amount, String note) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
		this.note = note;
	}

	public int totalWeight() {
		return weight * amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
