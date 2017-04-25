package source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import source.model.BagDataTxt;
import source.model.BagItem;

import java.io.IOException;

public class MainController {
    private BagDataTxt singleton;
    @FXML
    public MenuItem exitMenu;
    @FXML
    public Menu helpMenu;
    @FXML
    private ListView<BagItem> itemsList;
    @FXML
    private TextField nameField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField amountField;
    @FXML
    private TextArea noteField;
    @FXML
    private Label totalWeightLabel;
    @FXML
    public BorderPane mainPane;

    public MainController() {
        mainPane = new BorderPane();
        this.singleton = BagDataTxt.getInstance();
        itemsList = new ListView<BagItem>();
        singleton.addItem("Plecaczek", "1200", "1", "nicość");
        singleton.addItem("śpiwor", "900", "1", "zimno");
        singleton.addItem("karimata", "300", "1", "twardo :(");
        singleton.addItem("woda", "1500", "2", "ciężko");
    }

    public void initialize() {
        itemsList.setItems(singleton.getBagList().sorted());
        totalWeightLabel.setText(singleton.getTotalWeightG());
        reloadTotalWeightInKG();
        loadSelectedItem();
    }

    private void loadSelectedItem() {
        if (!itemsList.getSelectionModel().isEmpty()) {
            BagItem item = itemsList.getSelectionModel().getSelectedItem();
            nameField.setText(item.getName());
            weightField.setText(item.getWeight());
            amountField.setText(item.getAmount());
            noteField.setText(item.getNote());
        }
    }

    @FXML
    public void addNewItem() {
        singleton.addItem(nameField.getText(), weightField.getText(), amountField.getText(), noteField.getText());
        reloadTotalWeightInKG();
    }

    @FXML
    public void deleteItem() {
        singleton.deleteItem(itemsList.getSelectionModel().getSelectedItem());
        reloadTotalWeightInKG();
    }

    @FXML
    public void saveChangesMenu(ActionEvent actionEvent) {
        deleteItem();
        addNewItem();
    }

    @FXML
    public void saveMenu(ActionEvent actionEvent) {
        try {
            if (singleton.isFile)
                singleton.saveItemData();
            else saveToMenu(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToMenu(ActionEvent actionEvent) {
    }

    @FXML
    public void clearMenu(ActionEvent actionEvent) {
        nameField.clear();
        weightField.clear();
        amountField.clear();
        noteField.clear();
    }

    public void openMenu(ActionEvent actionEvent) {
        try {
            singleton.loadItemData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadTotalweightInG() {
        totalWeightLabel.setText(singleton.getTotalWeightG() + "g");
    }

    private void reloadTotalWeightInKG() {
        totalWeightLabel.setText(singleton.getTotalWeightKG() + "kg");
    }
}
