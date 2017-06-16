package source.model;

public class BagItem {
    private String name;
    private int weight;
    private int amount;
    private String note;

    BagItem(String name, String weight, String amount, String note) {
        this.name = name;
        this.weight = Integer.parseInt(weight);
        this.amount = Integer.parseInt(amount);
        this.note = note;
    }

    int getTotalWeight() {
        return this.weight * this.amount;
    }

    void editBody(String name, String weight, String amount, String note) {
        this.name = name;
        this.weight = Integer.parseInt(weight);
        this.amount = Integer.parseInt(amount);
        this.note = note;
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

