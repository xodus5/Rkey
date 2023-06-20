package haetaekey.com;

import java.util.PrimitiveIterator;

public class CafeData {
    private String menu;
    private String name;
    private String price;

    CafeData() {}

    public CafeData(String menu, String name, String price) {
        this.menu = menu;
        this.name = name;
        this.price = price;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
