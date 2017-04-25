package source.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class BagDataTxt implements BagData {
    private static BagDataTxt instance = new BagDataTxt();
    private String fileName;
    private int totalWeight;
    //    private Path path;
    private ObservableList<BagItem> bagListData;
    public boolean isFile;

    private BagDataTxt() {
        this.bagListData = FXCollections.observableArrayList();

        this.totalWeight = 0;
        this.isFile = false;
        this.fileName = "test.txt";
    }

    public static BagDataTxt getInstance() {
        return instance;
    }

    public ObservableList<BagItem> getBagList() {
        return bagListData;
    }

    public void setFileData(String fileName, Path path) {
        this.fileName = fileName;
//        this.path = path;
        this.isFile = true;
    }

    public String getTotalWeightG() {
        return Integer.toString(totalWeight);
    }

    public String getTotalWeightKG() {
        return String.format("%.3f",((double)totalWeight/1000));
    }

    private void calculateTotalWeight() {
        totalWeight = 0;
        for (BagItem temp : bagListData) {
            totalWeight += temp.getTotalWeight();
        }
    }

    @Override
    public void addItem(BagItem bagItem) {
        bagListData.add(bagItem);
        totalWeight += bagItem.getTotalWeight();
    }

    @Override
    public void addItem(String name, String weight, String amount, String note) {
        bagListData.add(new BagItem(name,Integer.parseInt(weight),Integer.parseInt(amount),note));
        totalWeight += (Integer.parseInt(amount) * Integer.parseInt(weight));
    }

    //    @Override
//    public void addItem(String name, String weight, String amount, String note) {
//        bagListData.add(new BagItem
//                .Builder(name)
//                .weight(weight)
//                .amount(amount)
//                .note(note)
//                .build());
//        calculateTotalWeight();
//    }

    @Override
    public void deleteItem(BagItem bagItem) {
        bagListData.remove(bagItem);
        totalWeight -= bagItem.getTotalWeight();
    }

    @Override
    public void saveItemData() throws IOException {
//        BufferedWriter bw = Files.newBufferedWriter(path);
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        Iterator<BagItem> iterator = bagListData.iterator();
        try {
            while (iterator.hasNext()) {
                BagItem item = iterator.next();
                bw.write(String.format("%s\t%s\t%s\t%s", item.getName(),
                        item.getWeight(),
                        item.getAmount(),
                        item.getNote()));
                bw.newLine();
            }
        } finally {
            bw.close();
        }
    }

    @Override
    public void loadItemData() throws IOException {

//        BufferedReader br = Files.newBufferedReader(path);
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try {
            while ((input = br.readLine()) != null) {
                String[] itemsPieces = input.split("\t");
                String m_name = itemsPieces[0];
                String m_weight = itemsPieces[1];
                String m_amount = itemsPieces[2];
                String m_note = itemsPieces[3];
                addItem(m_name, m_weight, m_amount, m_note);
            }
        } finally {
            br.close();
        }
        calculateTotalWeight();
    }

}
