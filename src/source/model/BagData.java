package source.model;

import java.io.File;
import java.io.IOException;

interface BagData {
    void addItem(BagItem bagItem);

    void addItem(String name, String weight, String amount, String note);

    void deleteItem(BagItem bagItem);

    void saveItemData() throws IOException;

    void loadItemData() throws IOException;

    void setFile(File file);

    boolean isFile();
}
