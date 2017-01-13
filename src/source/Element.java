package source;
/**
 * Created by Artur Artir Markowski on 2017-01-02.
 */
public class Element {
	private String name;
	private int weight;
	private int amount;

	private Element(final Builder builder) {
		super();
		this.name = builder.name;
		this.weight = builder.weight;
		this.amount = builder.amount;
	}

	int getTotalWeight() {
		return this.weight * this.amount;
	}

	String getName() {
		return name;
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

	@Override
	public String toString() {
		return "Element{" +
				"name='" + name + '\'' +
				", weight=" + weight +
				", amount=" + amount +
				'}';
	}

	static class Builder{
		private String name = " ";
		private int weight = 0;
		private int amount = 0;


		Builder(final String name){
			this.name = name;

		}

		Builder weight(final int weight){
			this.weight = weight;
			return this;
		}

		Builder amount(final int amount){
			this.amount = amount;
			return this;
		}


		Element build(){
			return new Element(this);
		}
	}
}
