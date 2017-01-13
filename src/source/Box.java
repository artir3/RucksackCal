package source;

import java.util.ArrayList;

class Box {
	private String boxName;
	private int boxWeight;
	private ArrayList<Element> box = null;


	private Box(final Builder builder) {
		this.boxName = builder.boxName;
		this.boxWeight = builder.boxWeight;
		this.box = builder.box;
	}

	void clearBag() {
		if(box.size()>0){
			this.box.clear();
			this.boxWeight = 0;
		}
	}

	void addElement(String name, int weight, int amount) {
		box.add(new Element.Builder(name).weight(weight).amount(amount).build());
		this.boxWeight += (weight * amount);
	}

	void removeBoxElement(String name) {
		int temp = indexOfItem(name);
		if (temp > -1) {
			this.boxWeight -= box.get(temp).getTotalWeight();
			this.box.remove(temp);
		}
	}

	void changeElementWeight(String name, int weight) {
		if (weight > -1)
			box.get(indexOfItem(name)).setWeight(weight);
		recalculateBagWeight();
	}

	void changeElementAmount(String name, int amount) {
		if (amount > -1) {
			box.get(indexOfItem(name)).setAmount(amount);
			recalculateBagWeight();
		}
	}

	void printBag() {
		if (!box.isEmpty()) {
			System.out.println(boxName + ", Amount of elements:" + box.size() +", Weight=" + boxWeight);
			System.out.printf("-> %1$-15s | %2$-8s | %3$-6s | %4$-8s \n", "name", "mass [g]", "amount",
					"Sum [g]");
			for (Element element : box) {
				System.out.printf("-> %1$-15s | %2$-8d | %3$-6d | %4$-8d \n", element.getName(),
						element.getWeight(), element.getAmount(), element.getTotalWeight());
			}
		} else {
			System.out.println("No find that bag.");
		}

	}

	private String getBoxName() {
		return this.boxName;
	}

	private int getBoxWeight() {
		return this.boxWeight;
	}

	private void recalculateBagWeight() {
		int temp = 0;
		for (Element element : box) {
			temp += element.getTotalWeight();
		}
		this.boxWeight = temp;
	}

	private int indexOfItem(String name) {
		int temp = -1;
		for (Element element : box) {
			if (name.equals(element.getName()))
				return box.indexOf(element);
		}
		return temp;
	}

	public static class Builder{
		private String boxName = " ";
		private int boxWeight = 0;
		private ArrayList<Element> box = null;

		public Builder(final String boxName){
			this.boxName = boxName;
			this.box = new ArrayList<>();
		}

		public Builder boxWeight(final int  boxWeight){
			this.boxWeight = boxWeight;
			return this;
		}

		public Builder boxList(final ArrayList<Element> newBox){
			this.box.addAll(newBox);
			return this;
		}

		public Box build(){
			return new Box(this);
		}
	}
}
