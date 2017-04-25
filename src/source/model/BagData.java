package source.model;

import java.io.IOException;


public interface BagData {
    void addItem(BagItem bagItem);

    void addItem(String name, String weight, String amount, String note);

    void deleteItem(BagItem bagItem);

    void saveItemData() throws IOException;

    void loadItemData() throws IOException;
}
