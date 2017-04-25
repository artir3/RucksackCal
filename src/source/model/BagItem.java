package source.model;

public class BagItem {
    private String name;
    private int weight;
    private int amount;
    private String note;

    public BagItem(String name, int weight, int amount, String note) {
        this.name = name;
        this.weight = weight;
        this.amount = amount;
        this.note = note;
    }

    public int getTotalWeight() {
        return this.weight * this.amount;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return Integer.toString(weight);
    }

    public String getAmount() {
        return Integer.toString(amount);
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
//}public class BagItem {
//	private String name;
//	private int weight;
//	private int amount;
//	private String note;
//
//	private BagItem(final Builder builder) {
//		super();
//		this.name = builder.name;
//		this.weight = builder.weight;
//		this.amount = builder.amount;
//		this.note = builder.note;
//	}
//
//	public int getTotalWeightG() {
//		return this.weight * this.amount;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public String getWeight() {
//		return Integer.toString(weight);
//	}
//
//	public String getAmount() {
//		return Integer.toString(amount);
//	}
//
//	public String getNote() {return note;	}
//
//	@Override
//	public String toString() {
//		return "BagItem{" +
//				"name='" + name + '\'' +
//				", weight=" + weight +
//				", amount=" + amount +
//				'}';
//	}
//
//	static class Builder{
//		private String name = "";
//		private int weight = 0;
//		private int amount = 0;
//		private String note= "";
//
//		Builder(final String name){
//			this.name = name;
//		}
//
//		Builder weight(final String weight){
//			this.weight = Integer.parseInt(weight);
//			return this;
//		}
//
//		Builder amount(final String amount){
//			this.amount = Integer.parseInt(amount);
//			return this;
//		}
//
//		Builder note(final String note){
//			this.note = note;
//			return this;
//		}
//		BagItem build(){
//			return new BagItem(this);
//		}
//	}
//}
