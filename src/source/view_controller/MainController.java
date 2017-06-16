package source.view_controller;

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
    private ListView<BagItem> itemsList;
    @FXML
    private TextField nameField, weightField, amountField;
    @FXML
    private TextArea noteField;
    @FXML
    private Label totalWeightLabel, errorLabel;
    @FXML
    public BorderPane mainPane = new BorderPane();

    private boolean validationPass = false;

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
        validation();
        if (validationPass) {
            singleton.addItem(nameField.getText(), weightField.getText(), amountField.getText(), noteField.getText());
            reloadTotalWeightInKG();
            validationPass = false;
            clearMenu();
        }
    }

    @FXML
    public void deleteItem() {
        singleton.deleteItem(itemsList.getSelectionModel().getSelectedItem());
        reloadTotalWeightInKG();
    }

    @FXML
    public void saveChangesMenu(ActionEvent actionEvent) {
        validation();
        if (!itemsList.getSelectionModel().isEmpty() && validationPass) {
            singleton.editItem(itemsList.getSelectionModel().getSelectedItem(), nameField.getText(), weightField.getText(), amountField.getText(), noteField.getText());
            reloadTotalWeightInKG();
            validationPass = false;
        }

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
            if (singleton.isFile()) {
                singleton.getBagList().clear();
                singleton.loadItemData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reloadTotalWeightInKG();
    }

    private File chosePathToOpenFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file with your bag.");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        return fileChooser.showOpenDialog(mainPane.getScene().getWindow());
    }

    private File chosePathToSaveFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save your bag to file.");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        return fileChooser.showSaveDialog(mainPane.getScene().getWindow());
    }

    @FXML
    public void clearMenu() {
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

    private void validation() {
        boolean nameBool, weightBool, amountBool;
        nameBool = weightBool = amountBool = false;
        if (nameField.getText().equalsIgnoreCase("")) {
            errorLabel.setText("Nie wpisano nazwy!");
        } else {
            nameBool = true;
        }

        if (weightField.getText().equalsIgnoreCase("")) {
            errorLabel.setText("Nie podano wagi!");
        } else if (weightField.getText().equalsIgnoreCase("0")) {
            errorLabel.setText("Podano zerową wagę!");
        } else if (!isNumber(weightField.getText())) {
            errorLabel.setText("Waga: Podaj wagę w gramach!");
        } else {
            weightBool = true;
        }

        if (amountField.getText().equalsIgnoreCase("")) {
            errorLabel.setText("Nie wpisano ilości!");
        } else if (amountField.getText().equalsIgnoreCase("0")) {
            errorLabel.setText("Podano zerową ilość!");
        } else if (!isNumber(amountField.getText())) {
            errorLabel.setText("Waga: Podaj wagę w gramach!");
        } else {
            amountBool = true;
        }

        if (nameBool && weightBool && amountBool) {
            validationPass = true;
            errorLabel.setText("");
        }
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void aboutMe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainPane.getScene().getWindow());
        alert.setTitle("O mnie");
        alert.setHeaderText("O mnie");
        String tekst = "Aplikacja RugsackCal powstała w celu szybkiego tworzenia listy przedmiotów które zabiera się ze sobą na wyprawy, przejażdżki czy wyjazdy. \n\n" +
                "Aplikacja automatycznie liczy wagę całego bagażu, który wpisaliśmy, co pozwala nam na łatwe kontrolowanie tego co można zabrać ze sobą, co jest " +
                "bardzo pomocne przy bagażu podręcznym przy przelotach samolotem.\n\n" +
                "RugsackCal. Wersja: 0.2.9.280417\n" +
                "Autor: Artur Markowski\n" +
                "Email: markowski.artur@outlook.com\n";
        alert.setContentText(tekst);
        alert.showAndWait();

    }
}
