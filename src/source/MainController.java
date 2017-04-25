package source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import source.model.BagDataTxt;
import source.model.BagItem;

import java.io.IOException;


public class MainController {
    private BagDataTxt singleton;
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
    private BorderPane mainPane;
    @FXML
    private Label totalWeightLabel;

    public MainController() {
        this.singleton = BagDataTxt.getInstance();
    }

    public void init(){
        itemsList.setItems(singleton.getBagList());

//        itemsList.getSelectionModel().getSelectedItems().addListener(observable -> {
//        });
        loadSelectedItem();
        totalWeightLabel.setText(singleton.getTotalWeight());
    }

    @FXML
    public void addNewItem(){
        singleton.addItem(nameField.getText(),weightField.getText(),amountField.getText(),noteField.getText());
        totalWeightLabel.setText(singleton.getTotalWeight()+"g");
    }

    @FXML
    public void deleteItem(){
        singleton.deleteItem(itemsList.getSelectionModel().getSelectedItem());
    }

    private void loadSelectedItem(){
        BagItem item = itemsList.getSelectionModel().getSelectedItem();
        nameField.setText(item.getName());
        weightField.setText(item.getWeight());
        amountField.setText(item.getAmount());
        noteField.setText(item.getNote());
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
}
