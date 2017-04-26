package source;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import source.model.BagDataTxt;
import source.model.BagItem;

import java.io.File;
import java.io.IOException;

public class MainController {
    private BagDataTxt singleton;

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
    public BorderPane mainPane = new BorderPane();

    public MainController() {
        this.singleton = BagDataTxt.getInstance();
        itemsList = new ListView<>();
    }

    public void initialize() {
        itemsList.setItems(singleton.getBagList().sorted());
        totalWeightLabel.setText(singleton.getTotalWeightG());
        loadSelectedItem();
        reloadTotalWeightInKG();
        loadSelectedItem();
    }

    private void loadSelectedItem() {
        itemsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!itemsList.getSelectionModel().isEmpty()) {
                BagItem item = itemsList.getSelectionModel().getSelectedItem();
                nameField.setText(item.getName());
                weightField.setText(item.getWeight());
                amountField.setText(item.getAmount());
                noteField.setText(item.getNote());
            }
        });

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
        if (!itemsList.getSelectionModel().isEmpty()) {
            deleteItem();
        }
        addNewItem();
    }

    @FXML
    public void saveMenu() {
        try {
            if (singleton.isFile())
                singleton.saveItemData();
            else saveToMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveToMenu() {
        try {
            singleton.setFile(chosePathToSaveFile());
            if (singleton.isFile()) saveMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openMenu(ActionEvent actionEvent) {
        try {
            singleton.setFile(chosePathToOpenFile());
            if (singleton.isFile()) singleton.loadItemData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reloadTotalWeightInKG();
    }

    private File chosePathToOpenFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file with your bag.");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        return fileChooser.showOpenDialog(mainPane.getScene().getWindow());
    }

    private File chosePathToSaveFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save your bag to file.");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        return fileChooser.showSaveDialog(mainPane.getScene().getWindow());
    }

    @FXML
    public void clearMenu(ActionEvent actionEvent) {
        nameField.clear();
        weightField.clear();
        amountField.clear();
        noteField.clear();
    }

    private void reloadTotalweightInG() {
        totalWeightLabel.setText(singleton.getTotalWeightG() + "g");
    }

    private void reloadTotalWeightInKG() {
        totalWeightLabel.setText(singleton.getTotalWeightKG() + "kg");
    }

    @FXML
    public void exitMenu(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
