package source;

public class Items {
	private String name;
	private int weight;
	private int amount;
	private String note;

	public Items(String name, int weight, int amount) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
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

	
	public String toString() {
		return "name=" + name + ", weight=" + weight + ", amount=" + amount + ", note=" + note + ", total weight="+totalWeight();
	}
	

}
